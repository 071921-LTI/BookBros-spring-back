package com.bookbros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.apis.BookAPI;
import com.bookbros.daos.BookRepository;
import com.bookbros.dtos.SelectedBook;
import com.bookbros.dtos.Work;
import com.bookbros.models.Book;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository br;
	private BookAPI ba;

	@Autowired
	public BookServiceImpl(BookRepository br, BookAPI ba) {
		super();
		this.br = br;
		this.ba = ba;
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
	public boolean createBook(Work work) {

		SelectedBook selectedBook = ba.getSelectedBook(work.getKey());

		Book newBook = new Book(selectedBook.getTitle(), work.getAuthor_name()[0], work.getPrice(), String.valueOf(work.getFirst_publish_year()), selectedBook.getDescription(), String.join(", ", selectedBook.getSubjects()), work.getInventory());
		
		br.save(newBook);

		if(br.findById(newBook.getId()) == null) {
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
