package com.tavant.spring.di.SpringDiDemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.spring.di.SpringDiDemo.entity.Book;

@Service
public class BookService {

	@Autowired
	private Book book;
	
//	@Autowired
	public BookService(Book book) {
		this.book = book;
	}
	
//	public Book getBook() {
//		return book;
//	}
	
//	@Autowired
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Book addBookDetails() {
		return this.book;
	}
	
	public void showBookDetails() {
		System.out.println(book.toString());
	}
	
}
