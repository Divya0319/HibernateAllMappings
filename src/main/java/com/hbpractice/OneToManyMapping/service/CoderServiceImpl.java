package com.hbpractice.OneToManyMapping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hbpractice.OneToManyMapping.dao.CoderDAO;
import com.hbpractice.OneToManyMapping.entity.BookReferred;
import com.hbpractice.OneToManyMapping.entity.Coder;
import com.hbpractice.OneToManyMapping.entity.CoderDetail;

@Service
public class CoderServiceImpl implements CoderService {
	
	private CoderDAO coderDao;

	@Autowired
	public CoderServiceImpl(CoderDAO coderDao) {
		this.coderDao = coderDao;
	}

	@Override
	@Transactional
	public void addCoder(Coder theCoder) {
		coderDao.addCoder(theCoder);
	}

	@Override
	@Transactional
	public boolean addCoderDetailToCoder(CoderDetail coderDetail, int coderId) {
		return coderDao.addCoderDetailToCoder(coderDetail, coderId);
	}

	@Override
	@Transactional
	public void addBookReferredToCoder(BookReferred booksReferred, int coderId) {
		coderDao.addBookReferredToCoder(booksReferred, coderId);
	}
	
	@Override
	@Transactional
	public List<Coder> fetchAllCoders() {
		return coderDao.fetchAllCoders();
	}

	@Override
	@Transactional
	public List<BookReferred> findAllBooks() {
		return coderDao.findAllBooks();
	}
	
	@Override
	@Transactional
	public Coder findCoderById(int theId) {
		return coderDao.findCoderById(theId);
	}

	@Override
	@Transactional
	public boolean deleteCoderById(int coderId) {

		return coderDao.deleteCoderById(coderId);
	}

	@Override
	@Transactional
	public boolean deleteBookById(int bookId) {
		return coderDao.deleteBookById(bookId);
	}

	@Override
	@Transactional
	public List<BookReferred> findBooksReferredByCoder(int coderId) {
		return coderDao.findBooksReferredByCoder(coderId);
	}

	@Override
	@Transactional
	public Coder findCoderOfBookById(int bookId) {
		return coderDao.findCoderOfBookById(bookId);
	}

}
