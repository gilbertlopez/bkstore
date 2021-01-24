package com.gillo.bookstore.load;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gillo.bookstore.model.Book;
import com.gillo.bookstore.model.Category;
import com.gillo.bookstore.repositories.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Gilbert Lopez
 *
 * Created on Dec 8, 2020
 */
@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	private final CategoryRepository categoryRepository;

	public DataLoader(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		log.debug("Loading Bootstrap Data");
		Category fantasyCategory = new Category();
		fantasyCategory.setName("Fantasy");
		Category programmingCategory = new Category();
		programmingCategory.setName("Programming");
		
		Book fantasyBook = new Book();	
		fantasyBook.setTitle("The Hobbit");
		fantasyBook.setAuthor("J.R.R. Tolkien");
		fantasyBook.setDescription("In a hole in the wall...");
		fantasyBook.setPublishedDate(LocalDate.of(1939, 1, 13));
		fantasyBook.setPrice(new BigDecimal(14.99));
		fantasyBook.setUnitsInStock(5);
		
		fantasyCategory.addBook(fantasyBook);
		categoryRepository.save(fantasyCategory);
		
		Book programmingBook = new Book();	
		programmingBook.setTitle("SQL Queries for Mere Mortals");
		programmingBook.setAuthor("Hernandez & Viescas");
		programmingBook.setDescription("This book will help new users with the foundations of SQL queries.");
		programmingBook.setPublishedDate(LocalDate.of(2000, 7, 20));
		programmingBook.setPrice(new BigDecimal(39.99));
		programmingBook.setUnitsInStock(10);
		
		programmingCategory.addBook(programmingBook);

		categoryRepository.save(programmingCategory);
		
		fantasyCategory = categoryRepository.findByName("Fantasy")
				.orElseThrow(RuntimeException::new);
		fantasyBook = new Book();	
		fantasyBook.setTitle("The Two Towers");
		fantasyBook.setAuthor("J.R.R. Tolkien");
		fantasyBook.setDescription("This is the second book in the Lord of the Rings trilogy.");
		fantasyBook.setPublishedDate(LocalDate.of(1951, 1, 13));
		fantasyBook.setPrice(new BigDecimal(19.99));
		fantasyBook.setUnitsInStock(6);
		
		fantasyCategory.addBook(fantasyBook);
		categoryRepository.save(fantasyCategory);
		
		log.debug("Finished Loading Bootstrap Data");
	}

}
