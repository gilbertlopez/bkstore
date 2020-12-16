package com.gillo.bookstore.services;

import java.util.Set;

import com.gillo.bookstore.model.Book;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 9, 2020
 */
public interface BookService {
	
	Set<Book> getAllBooks();
	
	Set<Book> getBooksByAuthor(String name);
	
	Book getBookById(Long id);
	
	public void addBook(Book book);
	
	public void deleteBook(Long id);
	
	public void updateBook(Book book);

}
