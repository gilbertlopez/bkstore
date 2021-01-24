package com.gillo.bookstore.services;

import org.springframework.stereotype.Service;

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
		return categoryRepository.findByName(name).orElse(addCategory(name));
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

}
