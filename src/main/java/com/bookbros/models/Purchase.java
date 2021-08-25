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
@Table(name = "purchases")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private int id;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=User.class )
    @JoinColumn(name="user_id", nullable = false)
	private User purchaser_id;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Book.class )
    @JoinColumn(name="book_id", nullable = false)
	private Book book;
	@Column(name = "date_purchased")
	private Date date_purchased;
	
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Purchase(User purchaser_id, Book book, Date date_purchased) {
		super();
		this.purchaser_id = purchaser_id;
		this.book = book;
		this.date_purchased = date_purchased;
	}

	public User getPurchaser_id() {
		return purchaser_id;
	}
	public void setPurchaser_id(User purchaser_id) {
		this.purchaser_id = purchaser_id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getDate_purchased() {
		return date_purchased;
	}
	public void setDate_purchased(Date date_purchased) {
		this.date_purchased = date_purchased;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((date_purchased == null) ? 0 : date_purchased.hashCode());
		result = prime * result + id;
		result = prime * result + ((purchaser_id == null) ? 0 : purchaser_id.hashCode());
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
		Purchase other = (Purchase) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (date_purchased == null) {
			if (other.date_purchased != null)
				return false;
		} else if (!date_purchased.equals(other.date_purchased))
			return false;
		if (id != other.id)
			return false;
		if (purchaser_id == null) {
			if (other.purchaser_id != null)
				return false;
		} else if (!purchaser_id.equals(other.purchaser_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", purchaser_id=" + purchaser_id + ", book=" + book + ", date_purchased="
				+ date_purchased + "]";
	}
}
