package com.gillo.bookstore.services;

import java.util.Set;

import com.gillo.bookstore.model.Book;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 9, 2020
 */
public interface BookService {
	
	Set<Book> getBooks();

}
