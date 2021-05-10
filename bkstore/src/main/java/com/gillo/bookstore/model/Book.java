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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min=2, max=45)
	private String title;
	
	@Size(min=2, max=45)
	private String author;
	
	@Size(max=250)
	private String description;
	
	@NotNull
	@DecimalMin(value = "0.00")
	@DecimalMax(value = "99.99")
	private BigDecimal price;
	
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate publishedDate;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 99)
	private Integer unitsInStock;
	
	@Lob
    private Byte[] image;
	
	@ManyToOne
	@NotNull
	private Category category;
}
