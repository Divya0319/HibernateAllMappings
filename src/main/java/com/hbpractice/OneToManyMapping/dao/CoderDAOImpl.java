package com.hbpractice.OneToManyMapping.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hbpractice.OneToManyMapping.entity.BookReferred;
import com.hbpractice.OneToManyMapping.entity.Coder;
import com.hbpractice.OneToManyMapping.entity.CoderDetail;

@Repository
public class CoderDAOImpl implements CoderDAO {
	
	private EntityManager entityManager;
	
	Logger theLogger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public CoderDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void addCoder(Coder theCoder) {
		Session addCoderSession = entityManager.unwrap(Session.class);
		
		addCoderSession.saveOrUpdate(theCoder);		
	}
	

	@Override
	public boolean addCoderDetailToCoder(CoderDetail coderDetail, int coderId) {
		Session fetchCoderForCoderDetailSession = entityManager.unwrap(Session.class);
		
		Coder tempCoder = fetchCoderForCoderDetailSession.get(Coder.class, coderId);
		
		if(tempCoder == null) {
			
			return false;
			
		} 
		
		tempCoder.setCoderDetail(coderDetail);
		
		Session updateSession = entityManager.unwrap(Session.class);
		
		updateSession.saveOrUpdate(tempCoder);
		
		return true;	
	}

	@Override
	public void addBookReferredToCoder(BookReferred bookReferred, int coderId) {
		Session fetchCoderSession = entityManager.unwrap(Session.class);
		
		Coder tempCoder = fetchCoderSession.get(Coder.class, coderId);
		
		tempCoder.addBook(bookReferred);
		
		Session updateSession = entityManager.unwrap(Session.class);
				
		updateSession.saveOrUpdate(tempCoder);
		
	}

	@Override
	public List<Coder> fetchAllCoders() {
		Session fetchAllCoderSession = entityManager.unwrap(Session.class);
		
		Query<Coder> theQuery = fetchAllCoderSession.createQuery("from Coder", Coder.class);
		
		List<Coder> coders = theQuery.getResultList();
		
		return coders;
	}
	
	public Coder findCoderById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Coder tempCoder = currentSession.get(Coder.class, theId);
				
		return tempCoder;

	}

	@Override
	public List<BookReferred> findAllBooks() {
		
		Session fetchAllBookSession = entityManager.unwrap(Session.class);
		
		Query<BookReferred> bookQuery = fetchAllBookSession.createQuery("from BookReferred", BookReferred.class);
		
		List<BookReferred> allBooks = bookQuery.getResultList();
		
		return allBooks;
	}

	@Override
	public boolean deleteCoderById(int coderId) {

		Session deleteSession = entityManager.unwrap(Session.class);
		
		Coder coder = findCoderById(coderId);
		
		if(coder == null) {
			
			return false;
		}
		
		List<BookReferred> booksOfCoder = coder.getBooksReferred();
		
		for(BookReferred tempBook : booksOfCoder) {
			
			tempBook.setCoder(null);
		
		}
		
		deleteSession.delete(coder);
		
		return true;
	}

	@Override
	public boolean deleteBookById(int bookId) {
		
		Session deleteBookSession = entityManager.unwrap(Session.class);
		
		BookReferred book = deleteBookSession.get(BookReferred.class, bookId);
		
		if(book == null) {
			
			return false;
		}
		
		deleteBookSession.delete(book);
		return true;
	}

	@Override
	public List<BookReferred> findBooksReferredByCoder(int coderId) {
		
		Session fetchBookSession = entityManager.unwrap(Session.class);
		
		Coder coder = fetchBookSession.get(Coder.class, coderId);
		
		if(coder == null) {
			
			return null;
		}
		
		return coder.getBooksReferred();
		
	}

	@Override
	public Coder findCoderOfBookById(int bookId) {
		
		Session fetchCoderSession = entityManager.unwrap(Session.class);
		
		BookReferred book = fetchCoderSession.get(BookReferred.class, bookId);
		
		if(book == null) {
			
			return null;
		}
		return book.getCoder();
	}

}
