package com.gillo.bookstore.controllers;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gillo.bookstore.model.Book;
import com.gillo.bookstore.model.Category;
import com.gillo.bookstore.services.BookService;
import com.gillo.bookstore.services.CategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 14, 2020
 */

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

	private final BookService bookService;
	private final CategoryService categoryService;

	public AdminController(BookService bookService, CategoryService categoryService) {
		this.bookService = bookService;
		this.categoryService = categoryService;
	}

	@GetMapping
	public String adminHome() {
		return "admin";
	}
	
	@GetMapping("bookInventory")
	public String bookInventory(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		return "bookInventory";
	}
	
	@GetMapping("book/{id}")
	public String bookDetail(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));	
		return "book";
	}
	
	@GetMapping("addBook")
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}
	
	@PostMapping("saveBook")
	public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {
		if(result.hasErrors()) return "addBook";
		
		Category category = categoryService.getCategoryByName(book.getCategory().getName());
		log.debug("category name is:"+category.getName());
		category.addBook(book);
		categoryService.updateCategory(category);
		model.addAttribute("success", true);
		
		return "redirect:/admin/bookInventory";
	}
	
	
	@GetMapping("deleteBook/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);	
		
		return "redirect:/admin/bookInventory";
	}

}
