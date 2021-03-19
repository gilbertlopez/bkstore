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
	
	Set<Book> getBooksByCategory(Long id);

	Book getBookById(Long id);

	void deleteBook(Long id);
	
	void saveBook(Book book);

}
