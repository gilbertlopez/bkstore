package com.gillo.bookstore.exceptions;

/**
 * @author Gilbert Lopez
 *
 * Created on Mar 2, 2021
 */
public class CategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CategoryNotFoundException() {
		super("Category not found");
	}


}
