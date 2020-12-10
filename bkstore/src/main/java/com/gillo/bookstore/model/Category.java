package com.gillo.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author Gilbert Lopez
 *
 * Created on Nov 4, 2020
 */

@Data
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Book> books;

	public Category addBook(Book book) {
		if (this.books == null) {
			this.books = new HashSet<>();
		}
		book.setCategory(this);
		this.books.add(book);
		return this;
	}
}
