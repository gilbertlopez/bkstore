package com.gillo.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gillo.bookstore.services.BookService;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 9, 2020
 */

@Controller
@RequestMapping("books")
public class AppController {
	
	private final BookService bookService;

	public AppController(BookService bookService) {
		this.bookService = bookService;
	}


	@GetMapping
	public String bookList(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		return "books";
	}
	
	@GetMapping("authors/{name}")
	public String authorList(@PathVariable String name, Model model) {
		model.addAttribute("books", bookService.getBooksByAuthor(name));	
		return "books";
	}
	
	@GetMapping("{id}")
	public String bookDetail(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));	
		return "book";
	}
}
