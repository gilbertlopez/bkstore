package com.gillo.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "category")
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "Book title is required")
	@Size(min=2, max=45, message = "Title must be between 2 and 45 characters long")
	private String title;
	
	@NotEmpty(message = "Author name is required")
	@Size(min=2, max=45)
	private String author;
	
	@Size(max=250)
	private String description;
	
	@DecimalMin(value = "0.00")
	@DecimalMax(value = "99.99")
	private BigDecimal price;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedDate;
	
	//@DecimalMax(value = "50", inclusive = true)
//	@NotNull
//	@Digits(integer = 100, fraction = 0)
	
	@Min(value = 0)
	@Max(value = 99)
	private int unitsInStock;
	
	@Lob
    private Byte[] image;
	
	@ManyToOne
	@NotBlank(message = "Category is required")
	@Size(min=2, max=45, message = "Category must be between 2 and 45 characters long")
	private Category category;
}
