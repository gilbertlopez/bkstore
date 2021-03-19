package com.gillo.bookstore.services;

import java.util.Set;

import com.gillo.bookstore.model.Category;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 19, 2021
 */
public interface CategoryService {
	
	Set<Category> getAllCategories();
	
	Set<String> getCategoryNames();

	Category getCategoryByName(String name);
	
	Category addCategory(String name);
	
	void updateCategory(Category category);
}
