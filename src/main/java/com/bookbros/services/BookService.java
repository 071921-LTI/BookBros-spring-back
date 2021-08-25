package com.bookbros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.daos.BookRepository;
import com.bookbros.models.Book;

@Service
public class BookService {
	
	private BookRepository br;

	@Autowired
	public BookService(BookRepository br) {
		super();
		this.br = br;
	}
	
	@Transactional
	public Book getByAuthorAndTitle(String author, String title) {
		return br.findByAuthorAndTitle(author, title);
	}
	
	@Transactional
	public List<Book> getBooks() {
		return br.findAll();
	}
	
	@Transactional
	public List<Book> getBooksByAuthor(String author) {
		return br.findAllByAuthor(author);
	}
	
	@Transactional
	public boolean createBook(Book b) {
		br.save(b);

		if(br.findById(b.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Transactional
	public boolean deleteBook(Book b) {
		br.delete(b);
		if(br.findById(b.getId()) == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateBook(Book b) {
		br.save(b);
		if (br.findByAuthorAndTitle(b.getAuthor(), b.getTitle()) == b) {
			return true;
		} else {
			return false;
		}
	}
}
