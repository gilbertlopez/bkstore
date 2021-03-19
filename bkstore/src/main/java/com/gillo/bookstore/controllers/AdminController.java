package com.gillo.bookstore.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gillo.bookstore.model.Book;
import com.gillo.bookstore.model.Category;
import com.gillo.bookstore.services.BookService;
import com.gillo.bookstore.services.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 14, 2020
 */

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("admin")
public class AdminController {

	private final BookService bookService;
	private final CategoryService categoryService;
	
	/*
	 * initbinder to trim inpout strings, remove leading and trailing whitespace
	 * used to resovle validation issue with integers
	 */

	/*
	 * @InitBinder private void initBinder(WebDataBinder dataBinder) {
	 * 
	 * StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	 * 
	 * dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); }
	 */
	
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
		model.addAttribute("categories", categoryService.getCategoryNames());
		return "addBook";
	}
	
	
	@PostMapping("saveBook")
	public String saveBook(@ModelAttribute("book") @Valid Book book, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("categories", categoryService.getCategoryNames());
			return "addBook";
		}
		
		System.out.println("description: "+book.getDescription());
		Category category = categoryService.getCategoryByName(book.getCategory().getName());
		category.addBook(book);
		categoryService.updateCategory(category);

		redirectAttributes.addFlashAttribute("message", "You successfully added " + book.getTitle() + "!");
		return "redirect:/admin/bookInventory";
	}
	
	
	@GetMapping("deleteBook/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);			
		return "redirect:/admin/bookInventory";
	}
	
	@GetMapping("editBook/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));	
		model.addAttribute("categories", categoryService.getCategoryNames());
		return "editBook";
	}	

	@PostMapping("updateBook")
	public String saveOrUpdateBook(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("categories", categoryService.getCategoryNames());
			return "editBook";
		}

		Category category = categoryService.getCategoryByName(book.getCategory().getName());
		Optional<Book> bookOptional = Optional.empty();
		
		if(category.getBooks() != null) {
			bookOptional = category.getBooks()
							.stream()
							.filter(bk -> bk.getId() == book.getId())
							.findFirst();
		}
		
		bookOptional.ifPresentOrElse(
			(bookfound) ->
		        { 
		        	log.debug("book exists, updating...");
			        bookfound.setTitle(book.getTitle());
			        bookfound.setAuthor(book.getAuthor());
			        bookfound.setDescription(book.getDescription());
			        bookfound.setPrice(book.getPrice());
			        bookfound.setPublishedDate(book.getPublishedDate());
			        bookfound.setUnitsInStock(book.getUnitsInStock());
		        }
	        , 
	        
		       	() -> {
		       		category.addBook(book);
		       		log.debug("Added book to new category...");	
		       		
		       	}
		);
			
		categoryService.updateCategory(category);

		return "redirect:/admin/bookInventory";
	}
}
