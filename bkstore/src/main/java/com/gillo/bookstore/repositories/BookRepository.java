package com.gillo.bookstore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gillo.bookstore.model.Book;

/**
 * @author Gilbert Lopez
 *
 * Created on Nov 5, 2020
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
	
	Optional<Book> findByTitle(String title);
	
	Iterable<Book> findByAuthor(String author);
	
	@Query("SELECT b FROM Book b WHERE b.category.id = :categoryId")
	Iterable<Book> findByCategory(@Param("categoryId") Long categoryId);

	
}
