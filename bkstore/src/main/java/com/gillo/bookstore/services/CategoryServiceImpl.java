package com.gillo.bookstore.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.gillo.bookstore.exceptions.CategoryNotFoundException;
import com.gillo.bookstore.model.Category;
import com.gillo.bookstore.repositories.CategoryRepository;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 19, 2021
 */

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}


	@Override
	public Category getCategoryByName(String name) {	
		return categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
	}


	@Override
	public Category addCategory(String name) {
		Category newCategory = new Category();
		newCategory.setName(name);
		return categoryRepository.save(newCategory);
	}


	@Override
	public void updateCategory(Category category) {
		categoryRepository.save(category);		
	}


	@Override
	public Set<Category> getAllCategories() {
		Set<Category> categories = new HashSet<>();
		categoryRepository.findAll().forEach(categories::add);
		return categories;
	}


	@Override
	public Set<String> getCategoryNames() {
		final Set<String> categoryNames = new HashSet<>();
		
		Iterable<Category> categories = categoryRepository.findAll();		
		for(Category category : categories) categoryNames.add(category.getName());
		
		return categoryNames;
	}

}
