package com.gillo.bookstore.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.gillo.bookstore.model.Book;
import com.gillo.bookstore.repositories.BookRepository;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 9, 2020
 */
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	@Override
	public Set<Book> getBooks() {
		Set<Book> books = new HashSet<>();
		bookRepository.findAll().forEach(books::add);
		//bookRepository.findAll().iterator().forEachRemaining(books::add);
		return books;
	}

}
