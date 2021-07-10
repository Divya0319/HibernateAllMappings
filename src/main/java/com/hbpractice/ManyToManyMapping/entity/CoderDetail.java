package com.hbpractice.ManyToManyMapping.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class CoderDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String githubProfileUrl;
	
	private int soRep;
	
	@OneToOne(mappedBy="coderDetail", cascade= {CascadeType.REFRESH,
												CascadeType.MERGE,
												CascadeType.DETACH,
												CascadeType.PERSIST})    // only this line, and below line is needed to make changes for bidirectional
	private Coder coder;				// mapping
	
	public CoderDetail() {
		
	}
	
	public CoderDetail(String githubProfileUrl, int soRep) {
		this.githubProfileUrl = githubProfileUrl;
		this.soRep = soRep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGithubProfileUrl() {
		return githubProfileUrl;
	}

	public void setGithubProfileUrl(String profileUrl) {
		this.githubProfileUrl = profileUrl;
	}

	public int getSoRep() {
		return soRep;
	}

	public void setSoRep(int soRep) {
		this.soRep = soRep;
	}
	
	

	public Coder getCoder() {
		return coder;
	}

	public void setCoder(Coder coder) {
		this.coder = coder;
	}

	@Override
	public String toString() {
		return "CoderDetail \n[\nid=" + id + ", profileUrl=" + githubProfileUrl + ", soRep=" + soRep + "\n]\n";
	}
		

}
