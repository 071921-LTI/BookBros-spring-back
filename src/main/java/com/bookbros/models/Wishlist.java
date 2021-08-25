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

@Entity
@Table(name = "wishes")
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "wish_id")
	private int id;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=User.class )
    @JoinColumn(name="user_id", nullable = false)
	private User wisher;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Book.class )
    @JoinColumn(name="book_id", nullable = false)
	private Book book;
	@Column(name = "date_added", nullable = false)
	private Date date_added;
	
	public Wishlist() {
		super();
	}
	public Wishlist(User wisher, Book book, Date date_added) {
		super();
		this.wisher = wisher;
		this.book = book;
		this.date_added = date_added;
	}

	public User getWisher() {
		return wisher;
	}
	public void setWisher(User wisher) {
		this.wisher = wisher;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getDate_added() {
		return date_added;
	}
	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((date_added == null) ? 0 : date_added.hashCode());
		result = prime * result + id;
		result = prime * result + ((wisher == null) ? 0 : wisher.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wishlist other = (Wishlist) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (date_added == null) {
			if (other.date_added != null)
				return false;
		} else if (!date_added.equals(other.date_added))
			return false;
		if (id != other.id)
			return false;
		if (wisher == null) {
			if (other.wisher != null)
				return false;
		} else if (!wisher.equals(other.wisher))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Wishlist [id=" + id + ", wisher=" + wisher + ", book=" + book + ", date_added=" + date_added + "]";
	}
}
