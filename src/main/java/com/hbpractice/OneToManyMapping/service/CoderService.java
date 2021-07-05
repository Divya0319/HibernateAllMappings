package com.hbpractice.OneToManyMapping.service;

import java.util.List;

import com.hbpractice.OneToManyMapping.entity.BookReferred;
import com.hbpractice.OneToManyMapping.entity.Coder;
import com.hbpractice.OneToManyMapping.entity.CoderDetail;

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


}
