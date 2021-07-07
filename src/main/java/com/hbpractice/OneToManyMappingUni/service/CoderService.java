package com.hbpractice.OneToManyMappingUni.service;

import java.util.List;

import com.hbpractice.OneToManyMappingUni.entity.BookReferred;
import com.hbpractice.OneToManyMappingUni.entity.BookReview;
import com.hbpractice.OneToManyMappingUni.entity.Coder;
import com.hbpractice.OneToManyMappingUni.entity.CoderDetail;

public interface CoderService {
	
	public void addCoder(Coder theCoder);
	
	public boolean addCoderDetailToCoder(CoderDetail coderDetail, int coderId);

	public void addBookReferredToCoder(BookReferred booksReferred, int coderId);
	
	public List<Coder> fetchAllCoders();
	
	public Coder findCoderById(int theId);

	public List<BookReferred> findAllBooks();

	public boolean deleteCoderById(int coderId);

	public boolean deleteBookById(int bookId);
	
	public List<BookReferred> findBooksReferredByCoder(int coderId);

	public Coder findCoderOfBookById(int bookId);

	public boolean deleteCoderDetailById(int coderDetailId);

	public boolean updateCoder(Coder coder);

	public Coder findCoderForCoderDetailById(int coderDetailId);

	public CoderDetail fetchCoderDetailForCoder(int coderId);

	public BookReferred findBookById(int bookId);

	public boolean addBookReview(BookReview bookReview, int bookId);


}
