package com.gillo.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gillo.bookstore.model.Category;

/**
 * @author Gilbert Lopez
 *
 * Created on Nov 5, 2020
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByName(String name);
}
