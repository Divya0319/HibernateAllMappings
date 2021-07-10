package com.hbpractice.OneToManyMappingUni.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonInclude(Include.NON_NULL)
public class Coder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="coder_detail_id")
	private CoderDetail coderDetail;
	
	@OneToMany(mappedBy="coder", cascade= {
										CascadeType.REFRESH,
										CascadeType.PERSIST,
										CascadeType.DETACH,
										CascadeType.MERGE
											})
	private List<BookReferred> booksReferred;
	
	
	public Coder() {
		
	}
	
	public Coder(String firstName, String lastName, int age, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public CoderDetail getCoderDetail() {
		return coderDetail;
	}

	public void setCoderDetail(CoderDetail coderDetail) {
		this.coderDetail = coderDetail;
	}

	
	public List<BookReferred> getBooksReferred() {
		return booksReferred;
	}

	public void setBooksReferred(List<BookReferred> booksReferred) {
		this.booksReferred = booksReferred;
	}

	public void addBook(BookReferred bookReferred) {
		
		if(booksReferred == null) {
			
			booksReferred = new ArrayList<>();
		}
		
		booksReferred.add(bookReferred);
		
		bookReferred.setCoder(this);

	}

	@Override
	public String toString() {
		return "\nCoder [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", email=" + email + ", coderDetail=" + coderDetail + ", booksReferred=" + booksReferred + "]\n";
	}

}
