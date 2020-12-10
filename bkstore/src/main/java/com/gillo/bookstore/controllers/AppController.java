package com.gillo.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gillo.bookstore.services.BookService;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 9, 2020
 */
@Controller
public class AppController {
	
	private BookService bookService;

	public AppController(BookService bookService) {
		this.bookService = bookService;
	}


	@GetMapping("books")
	public String bookList(Model model) {
		model.addAttribute("books", bookService.getBooks());
		return "books";
	}
}
