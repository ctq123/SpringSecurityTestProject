package com.test.ssm.entity;

public class Comment {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	/**
	 * ÄÚÈÝ
	 */
	private String content;
	/**
	 * ×÷Õß
	 */
	private String author;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
