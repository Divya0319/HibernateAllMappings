package com.hbpractice.ManyToManyMapping.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class BookReferred {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="coder_id")
	private Coder coder;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="book_id")
	private List<BookReview> bookReviews;
	
	
	@ManyToMany(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="bookreferred_designer",
				joinColumns=@JoinColumn(name="book_id"),
				inverseJoinColumns=@JoinColumn(name="designer_id")
	)
	private List<Designer> designers;
	
	public BookReferred() {
		
	}

	public BookReferred(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Coder getCoder() {
		return coder;
	}

	public void setCoder(Coder coder) {
		this.coder = coder;
	}
	
	

	public List<BookReview> getBookReviews() {
		return bookReviews;
	}

	public void setBookReviews(List<BookReview> bookReviews) {
		this.bookReviews = bookReviews;
	}
	
	public void addBookReview(BookReview tempReview) {
		
		if(bookReviews == null) {
			
			bookReviews = new ArrayList<BookReview>();
		}
		
		bookReviews.add(tempReview);
		
	}
	
	

	public List<Designer> getDesigners() {
		return designers;
	}

	public void setDesigners(List<Designer> designers) {
		this.designers = designers;
	}
	

	@Override
	public String toString() {
		return "BooksReferred \n[\nid=" + id + ", title=" + title +  "\n]";
	}
	
	

}
