package com.gillo.bookstore.exceptions;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 29, 2021
 */
public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BookNotFoundException() {
		super("Book not found");
	}

}
