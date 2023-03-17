package com.ezen.springmvc.model;

import org.springframework.stereotype.Component;

// 스프링이 수거해 갈 수 있도록 어노테이션으로 표시를 해둔다.
@Component
public class Book {

	private String book_name;
	private String author;
	private Integer pages;
	private String publisher;
	
	public Book() {}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
