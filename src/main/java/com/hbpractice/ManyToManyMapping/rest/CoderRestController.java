package com.hbpractice.ManyToManyMapping.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbpractice.ManyToManyMapping.entity.BookReferred;
import com.hbpractice.ManyToManyMapping.entity.BookReview;
import com.hbpractice.ManyToManyMapping.entity.Coder;
import com.hbpractice.ManyToManyMapping.entity.CoderDetail;
import com.hbpractice.ManyToManyMapping.entity.Designer;
import com.hbpractice.ManyToManyMapping.entity.ResponseObject;
import com.hbpractice.ManyToManyMapping.service.CoderService;


@RestController
@RequestMapping("/api")
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
	
	@GetMapping("/coders/{coderId}")
	public List<?> fetchCoderById(@PathVariable int coderId, HttpServletResponse response) {
		
		ResponseObject res = new ResponseObject();

		
		Coder coder = coderService.findCoderById(coderId); 
		Coder tempCoder = coder;
		
		if(tempCoder == null) {
			
			List<ResponseObject> result = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			res.setMessage("No coder available with given id");
			
			result.add(res);
			
			return result;
		}
		
		List<Coder> result = new ArrayList<>();
		
		response.setStatus(HttpStatus.OK.value());
		
		tempCoder.setCoderDetail(null);
		
		tempCoder.setBooksReferred(null);
		
		result.add(tempCoder);
		
		return result;
		
	}
	
	@GetMapping("/designers")
	public List<?> fetchAllDesigners(HttpServletResponse response) {
		
		List<Designer> designers = coderService.fetchAllDesigners();
		
		List<Designer> tempDesigners = designers;

		if(designers.size() == 0) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			ResponseObject res = new ResponseObject();
			res.setMessage("No designers available");
			
			List<ResponseObject> resObj = new ArrayList<>();
			resObj.add(res);
			
			return resObj;
	
		}
		
		for(Designer designer : tempDesigners) {
			
			designer.setBooksReferred(null);
		}
		
		response.setStatus(HttpStatus.OK.value());
		
		return tempDesigners;
	}
	
	@GetMapping("/coderDetail/{coderDetailId}/coders")
	public List<?> fetchCoderForGivenCoderDetail(@PathVariable int coderDetailId, HttpServletResponse response) {
		ResponseObject res = new ResponseObject();
		
		Coder coder = coderService.findCoderForCoderDetailById(coderDetailId);
		
		Coder tempCoder = coder;
		
		
		if(coder == null) {
			
			List<ResponseObject> result = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			res.setMessage("No coder available with given id");
			
			result.add(res);
			
			return result;
			
		
		} 
		
		List<Coder> result = new ArrayList<>();
		
		response.setStatus(HttpStatus.OK.value());
		
		tempCoder.setCoderDetail(null);
		
		tempCoder.setBooksReferred(null);
		
		result.add(tempCoder);
		
		return result;
		
			
	}
	
	@GetMapping("/coders/{coderId}/coderDetail")
	public List<?> fetchCoderDetailForCoder(@PathVariable int coderId, HttpServletResponse response) {
		ResponseObject res = new ResponseObject();
		
		
		CoderDetail coderDetail = coderService.fetchCoderDetailForCoder(coderId);
		
		CoderDetail tempDetail = coderDetail;
		
		if(coderDetail == null) {
			
			List<ResponseObject> result = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			res.setMessage("No coderDetail available for coder with id : " + coderId);
			
			result.add(res);
			
			return result;
			
		}
						
		List<CoderDetail> result = new ArrayList<>();
		
		response.setStatus(HttpStatus.OK.value());
		
		tempDetail.setCoder(null);
		
		result.add(tempDetail);
		
		return result;
	}
	
	
	@GetMapping("/booksReferred")
	public List<BookReferred> findAllBooks() {
		
		List<BookReferred> allBooks = coderService.findAllBooks();
		
		List<BookReferred> tempBooks = allBooks;
		
		if(allBooks.size() != 0) {
		
			for(BookReferred tempBook : tempBooks) {
			
				tempBook.setCoder(null);
				tempBook.setDesigners(null);
			}
		}
		
		
		return tempBooks;
	}
	
	@GetMapping("/coders/{coderId}/booksReferred")
	public List<?> fetchAllBooksOfCoder(@PathVariable int coderId, HttpServletResponse response) {
		
		ResponseObject resObj = new ResponseObject();
		
		List<BookReferred> booksReferred = coderService.findBooksReferredByCoder(coderId);
		
		List<BookReferred> tempBooks = booksReferred;
		
		if(tempBooks.size() == 0) {
			
			List<ResponseObject> res = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			resObj.setMessage("No books found for given coder with id : " + coderId + " or no coder found with this id");
			res.add(resObj);
			
			return res;
		}
		
		for(BookReferred tempBook : tempBooks) {
			
			tempBook.setCoder(null);
			tempBook.setDesigners(null);
		}
		
		response.setStatus(HttpStatus.OK.value());
		
		return tempBooks;
		
	}
	
	@GetMapping("/booksReferred/coders")
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
	
	@GetMapping("/booksReferred/{bookId}/bookReviews")
	public List<?> fetchAllReviewsForABook(@PathVariable int bookId, 
			HttpServletResponse response) {
		
		ResponseObject resp = new ResponseObject();
		
		List<BookReview> bookReviews = coderService.findReviewsForBook(bookId);
		
		
		if(bookReviews.size() == 0) {
			
			List<ResponseObject> resObj = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			resp.setMessage("Book not found with id : " + bookId + " or there are no reviews available");
			
			resObj.add(resp);
			
			return resObj;
			
		}
		
		response.setStatus(HttpStatus.OK.value());
	
		return bookReviews;
	}
	
	@GetMapping("/designers/{dId}/booksReferred")
	public List<?> fetchAllBooksForDesigner(@PathVariable int dId,
			HttpServletResponse response) {
		
		ResponseObject resp = new ResponseObject();
		
		List<BookReferred> booksReferred = coderService.findAllBooksForDesigner(dId);
		
		List<BookReferred> tempBooks = booksReferred;
		
		if(tempBooks.size() == 0) {
			
			List<ResponseObject> resObj = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			resp.setMessage("Books not found for designer with id : " + dId );
			
			resObj.add(resp);
			
			return resObj;
			
		}
		
		for(BookReferred tempBook : tempBooks) {
			
			tempBook.setCoder(null);
			tempBook.setDesigners(null);
		}
		
		response.setStatus(HttpStatus.OK.value());
	
		return tempBooks;
		
	}
	
	@GetMapping("/booksReferred/{bId}/designers")
	public List<?> fetchAllDesignersForBook(@PathVariable int bId,
			HttpServletResponse response) {
		
		ResponseObject resp = new ResponseObject();
		
		List<Designer> designers = coderService.findAllDesignersForBook(bId);
		
		List<Designer> tempDesigners = designers;
		
		
		if(tempDesigners.size() == 0) {
			
			List<ResponseObject> resObj = new ArrayList<>();
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			resp.setMessage("Designer not found for book with id : " + bId );
			
			resObj.add(resp);
			
			return resObj;
			
		}
		
		for(Designer tempDesigner : tempDesigners) {
			
			tempDesigner.setBooksReferred(null);

		}
		
		response.setStatus(HttpStatus.OK.value());
	
		return tempDesigners;
		
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
	
	@PostMapping("/coders/{coderId}/booksReferred")
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
		
		
		for(BookReferred tempBook : books) {
						
			
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
	
	@PostMapping("/booksReferred/{bookId}/bookReviews")
	public ResponseObject addBookReview(@RequestBody BookReview bookReview, 
			@PathVariable int bookId,
			HttpServletResponse response) {
		
		ResponseObject resObj = new ResponseObject();
		
		boolean bookFound = coderService.addBookReview(bookReview, bookId);
		
		if(!bookFound) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			resObj.setMessage("Book not found with id : " + bookId);
					
			return resObj;
		}
		
		
		response.setStatus(HttpStatus.OK.value());
		resObj.setMessage("Book review added successfully to book with id : " + bookId);
				
		return resObj;
	}
	
	@PostMapping("/designers")
	public Designer addDesigner(@RequestBody Designer designer, HttpServletResponse response) {
		
		designer.setId(0);
		
		coderService.addDesigner(designer);
		
		return designer;
		
	}
	
	@PostMapping("/designers/{designerId}/booksReferred")
	public ResponseObject addBookToDesigner(@RequestBody BookReferred bookReferred, 
			@PathVariable int designerId,
			HttpServletResponse response) {
		
		ResponseObject resObj = new ResponseObject();
		
		Designer designer = coderService.findDesignerById(designerId);
		
		if(designer == null) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			resObj.setMessage("Designer not found with id : " + designerId);
					
			return resObj;
		}
		
		List<BookReferred> books = coderService.findAllBooks();
		
		for(BookReferred tempBook : books) {
						
			
			if(tempBook.getTitle().equals(bookReferred.getTitle())) {
				
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				resObj.setMessage("Duplicate title not allowed");
						
				return resObj;
				
			}
			
		}
		
		coderService.addBookToDesigner(bookReferred, designerId);
		
		response.setStatus(HttpStatus.OK.value());
		resObj.setMessage("Book added successfully to designer with id : " + designerId);
				
		return resObj;
	}
	
	@PutMapping("/coders")
	public ResponseObject updateCoder(@RequestBody Coder coder, HttpServletResponse response) {
		
		boolean coderFound = coderService.updateCoder(coder);
		
		ResponseObject resp = new ResponseObject();
		
		if(!coderFound) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			
			resp.setMessage("Coder not found with id : " + coder.getId() );
			
			return resp;
		}
		
		response.setStatus(HttpStatus.OK.value());
		resp.setMessage("Coder with id : " + coder.getId() + " updated successfully");
				
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
	
	@DeleteMapping("/coderDetail/{coderDetailId}")
	public List<?> deleteCoderDetailById(@PathVariable int coderDetailId, HttpServletResponse response) {
		
		ResponseObject res = new ResponseObject();
		
		boolean coderDetailFound = coderService.deleteCoderDetailById(coderDetailId);
		
		List<ResponseObject> responseToSend = new ArrayList<>();
		
		if(!coderDetailFound) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			res.setMessage("CoderDetail with this id not found");
			
			responseToSend.add(res);
			
			return responseToSend;
		}
		
		response.setStatus(HttpStatus.OK.value());
		res.setMessage("CoderDetail with this id : " + coderDetailId + " deleted successfully");
		
		responseToSend.add(res);
		
		return responseToSend;
	}
	
	@DeleteMapping("/designers/{dId}")
	public ResponseObject deleteDesignerById(@PathVariable int dId, HttpServletResponse response) {
		
		ResponseObject res = new ResponseObject();
		
		boolean designerDeleted = coderService.deleteDesignerById(dId);
		
		if(!designerDeleted) {
			
			response.setStatus(HttpStatus.NOT_FOUND.value());
			res.setMessage("Designer with id: " + dId + " not found");
			
			
			return res;
		}
		
		response.setStatus(HttpStatus.OK.value());
		res.setMessage("Designer with this id : " + dId + " deleted successfully");
		
		
		return res;
		
	}
		

}
