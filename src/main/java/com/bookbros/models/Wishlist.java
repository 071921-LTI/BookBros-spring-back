package com.bookbros.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "wishes")
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "wish_id")
	private int id;
	@ManyToOne(targetEntity=User.class )
    @JoinColumn(name="user_id", nullable = false)
	private User wisher;
	@ManyToOne(targetEntity=Book.class )
    @JoinColumn(name="book_id", nullable = false)
	private Book book;
	@Column(name = "date_added", updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private LocalDate dateAdded;
}
