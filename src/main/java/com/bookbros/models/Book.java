package com.bookbros.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;
	@NotNull @NotBlank
	@Column(name = "title", nullable = false, unique = true)
	private String title;
	@Column(name = "author", nullable = false)
	private String author;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "published", nullable = false)
	private String published;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "subjects", nullable = false)
	private String subjects;
	@Column(name = "inventory")
	private int inventory;
}
