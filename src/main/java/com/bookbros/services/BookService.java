package com.bookbros.services;

import java.util.List;

import com.bookbros.dtos.Work;
import com.bookbros.exceptions.BookNotFoundException;
import com.bookbros.models.Book;

public interface BookService {
	public abstract Book getByAuthorAndTitle(String author, String title) throws BookNotFoundException;
	public abstract List<Book> getBooks();
	public abstract List<Book> getBooksWithInventory();
	public abstract List<Book> getBooksByAuthor(String author);
	public abstract List<Book> findByTitleContaining(String title);
	public abstract List<Book> findByTitleContainingAndInventoryGreaterThan(String title, int inventory);
	public abstract List<Book> findByAuthorContaining(String author);
	public abstract List<Book> findByAuthorContainingAndInventoryGreaterThan(String author, int inventory);
	public abstract List<Book> findBySubjectsContaining(String subject);
	public abstract List<Book> findBySubjectsContainingAndInventoryGreaterThan(String subject, int inventory);
	public abstract boolean createBook(Work work);
	public abstract boolean deleteBook(Book b) throws BookNotFoundException;
	public abstract boolean updateBook(Book b) throws BookNotFoundException;
}
