package com.gillo.bookstore.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gillo.bookstore.model.Book;

/**
 * @author Gilbert Lopez
 *
 * Created on Nov 5, 2020
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
