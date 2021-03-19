package com.gillo.bookstore.services;

import java.util.HashSet;
import java.util.Optional;
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
	public Set<Book> getAllBooks() {
		Set<Book> books = new HashSet<>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}


	@Override
	public Set<Book> getBooksByAuthor(String name) {
		Set<Book> books = new HashSet<>();
		bookRepository.findByAuthor(name).forEach(books::add);
		return books;
	}
	
	@Override
	public Set<Book> getBooksByCategory(Long id) {
		Set<Book> books = new HashSet<>();
		bookRepository.findByCategory(id).forEach(books::add);
		return books;
	}

	@Override
	public Book getBookById(Long id) {
		Optional<Book> optBook = bookRepository.findById(id);
		return optBook.get();
	}


	@Override
	public void saveBook(Book book) {
		bookRepository.save(book);
	}


	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}



}
