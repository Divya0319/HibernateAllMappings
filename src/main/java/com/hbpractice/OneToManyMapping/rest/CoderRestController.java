package com.hbpractice.OneToManyMapping.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbpractice.OneToManyMapping.entity.BookReferred;
import com.hbpractice.OneToManyMapping.entity.Coder;
import com.hbpractice.OneToManyMapping.entity.CoderDetail;
import com.hbpractice.OneToManyMapping.entity.ResponseObject;
import com.hbpractice.OneToManyMapping.service.CoderService;


@RestController
@RequestMapping("/oneToMany")
public class CoderRestController {
	
private CoderService coderService;
	
	
	@Autowired
	public CoderRestController(CoderService coderService) {
		this.coderService = coderService;
	}
	
	
	@GetMapping("/coders")
	public List<?> fetchAllCoders(HttpServletResponse response) {
		
		List<Coder> coders = coderService.fetchAllCoders();
		
		
		List<Coder> tempCoders = coders;

		if(coders.size() == 0) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			ResponseObject res = new ResponseObject();
			res.setMessage("No coders available");
			
			List<ResponseObject> resObj = new ArrayList<>();
			resObj.add(res);
			
			return resObj;
	
		}
		
		for(Coder coder : tempCoders) {
			
			coder.setCoderDetail(null);
			coder.setBooksReferred(null);
		}
		
		response.setStatus(HttpStatus.OK.value());
		
		return tempCoders;
	}
	
	@GetMapping("/booksReferred")
	public List<BookReferred> findAllBooks() {
		
		List<BookReferred> allBooks = coderService.findAllBooks();
		
		List<BookReferred> tempBooks = allBooks;
		
		if(allBooks.size() != 0) {
		
			for(BookReferred tempBook : tempBooks) {
			
				tempBook.setCoder(null);
			}
		}
		
		
		return tempBooks;
	}
	

	@PostMapping("/coders")
	public Coder addCoder(@RequestBody Coder coder) {
		
		coder.setId(0);
		
		coderService.addCoder(coder);
		
		return coder;
		
	}
	
	@PostMapping("/coders/{coderId}/coderDetail")
	public ResponseObject addCoderDetail(@RequestBody CoderDetail coderDetail, @PathVariable int coderId,
											HttpServletResponse response) {
		ResponseObject resp = new ResponseObject();
		
		boolean coderFound = coderService.addCoderDetailToCoder(coderDetail, coderId);
		
		if(!coderFound) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			resp.setMessage("Coder not found with id : " + coderId );
			
			return resp;
		}
		
		response.setStatus(HttpStatus.OK.value());
		resp.setMessage("Coder Detail added successfully to coder with id : " + coderId);
				
		return resp;
	}
	
	@PostMapping("/coders/{coderId}/bookReferred")
	public ResponseObject addBookReferredToCoder(@RequestBody BookReferred booksReferred, @PathVariable int coderId,
											HttpServletResponse response) {
		
		ResponseObject resp = new ResponseObject();
		
		Coder coder = coderService.findCoderById(coderId);
		
		if(coder == null) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			resp.setMessage("Coder not found with id : " + coderId);
					
			return resp;
			
		}

		
		List<BookReferred> books = coderService.findAllBooks();
		
		List<BookReferred> tempBooks = books;
		
		for(BookReferred tempBook : tempBooks) {
						
			
			if(tempBook.getTitle().equals(booksReferred.getTitle())) {
				
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				resp.setMessage("Duplicate title not allowed");
						
				return resp;
				
			}
			
		}
		
		
				
		coderService.addBookReferredToCoder(booksReferred, coderId);
		
		
		response.setStatus(HttpStatus.OK.value());
		resp.setMessage("Book added successfully to coder with id : " + coderId);
				
		return resp;
		
	}
	
	@DeleteMapping("/coders/{coderId}")
	public ResponseObject deleteCoderById(@PathVariable int coderId, HttpServletResponse response) {
		
		ResponseObject resObj = new ResponseObject();
		
		boolean coderDeleted =  coderService.deleteCoderById(coderId);
		
		if(!coderDeleted) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			resObj.setMessage("Coder not found with id : " + coderId);
			
			return resObj;
			
		} 
		 
		response.setStatus(HttpStatus.OK.value());

		resObj.setMessage("Coder with id : " + coderId + " deleted successfully");
		
		return resObj;
	}
	
	@DeleteMapping("/booksReferred/{bookId}")
	public ResponseObject deleteBookById(@PathVariable int bookId, HttpServletResponse response) {
		
		ResponseObject resObj = new ResponseObject();
		
		boolean bookDeleted = coderService.deleteBookById(bookId);
		
		if(!bookDeleted) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			resObj.setMessage("Book not found with id : " + bookId);
			
			return resObj;
			
		} 
		 
		response.setStatus(HttpStatus.OK.value());

		resObj.setMessage("Book with id : " + bookId + " deleted successfully");
		
		return resObj;
	}
	
	@GetMapping("/coders/{coderId}/bookReferred")
	public List<?> fetchAllBooksOfCoder(@PathVariable int coderId, HttpServletResponse response) {
		
		ResponseObject resObj = new ResponseObject();
		
		List<BookReferred> booksReferred = coderService.findBooksReferredByCoder(coderId);
		
		List<BookReferred> tempBooks = booksReferred;
		
		if(tempBooks == null) {
			
			List<ResponseObject> res = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			resObj.setMessage("No books found for given coder with id : " + coderId);
			res.add(resObj);
			
			return res;
		}
		
		for(BookReferred tempBook : tempBooks) {
			
			tempBook.setCoder(null);
		}
		
		response.setStatus(HttpStatus.OK.value());
		
		return tempBooks;
		
	}
	
	@GetMapping("/bookReferred/coder")
	public List<?> fetchCoderOfBook(@RequestParam("bookId") int bookId, HttpServletResponse response) {
		
		ResponseObject resObj = new ResponseObject();
		
		Coder coder = coderService.findCoderOfBookById(bookId);
		

		Coder tempCoder = coder;
		if(tempCoder == null) {
			
			List<ResponseObject> res = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			resObj.setMessage("No coder found for given book with id : " + bookId);
			res.add(resObj);
			
			return res;
		}
		
		List<Coder> coders = new ArrayList<>();
		
		tempCoder.setBooksReferred(null);
		tempCoder.setCoderDetail(null);
		
		coders.add(tempCoder);
		
		response.setStatus(HttpStatus.OK.value());
		
		return coders;
		
	}
	

}
