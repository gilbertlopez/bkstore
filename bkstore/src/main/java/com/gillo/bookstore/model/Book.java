package com.gillo.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "category")
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Size(min=2, max=45)
	private String title;
	@Size(min=2, max=45)
	private String author;
	@Size(max=250)
	private String description;
	private String edition;
	@NotNull
	private BigDecimal price;
	private LocalDate publishedDate;
	@NotNull
	private int unitInStock;
	
	@Lob
    private Byte[] image;
	
	@ManyToOne
	private Category category;
}
