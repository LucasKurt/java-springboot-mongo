package com.lucaslearning.Workshop_Mongo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String msg;
	private Date date;
	private AuthorDTO author;
	
	public CommentDTO() {
	}
	
	public CommentDTO(String msg, Date date, AuthorDTO author) {
		this.msg = msg;
		this.date = date;
		this.author = author;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
}
