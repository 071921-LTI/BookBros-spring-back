package com.bookbros.models;

import java.sql.Date;

import javax.persistence.CascadeType;
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
@Table(name = "purchases")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private int id;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=User.class )
    @JoinColumn(name="user_id", nullable = false)
	private User purchaser;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Book.class )
    @JoinColumn(name="book_id", nullable = false)
	private Book book;
	@Column(name = "date_purchased", updatable=false, columnDefinition="timestamp default CURRENT_TIMESTAMP")
	private Date datePurchased;
	
	public Purchase() {
		super();
	}
	
	public Purchase(User purchaser, Book book) {
		super();
		this.purchaser = purchaser;
		this.book = book;
	}
}
