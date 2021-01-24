package com.gillo.bookstore.services;

import com.gillo.bookstore.model.Category;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 19, 2021
 */
public interface CategoryService {

	Category getCategoryByName(String name);
	
	Category addCategory(String name);
	
	void updateCategory(Category category);
}
