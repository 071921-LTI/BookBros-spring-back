package com.bookbros.daos;

import java.util.List;

import com.bookbros.exceptions.BookNotFoundException;
import com.bookbros.models.Book;

public interface BookDao {
	public abstract Book getBookById(int id) throws BookNotFoundException;
	public abstract Book getBookByTitleAuthor (String title, String author) throws BookNotFoundException;
	public abstract List<Book> getBooks();
	public abstract List<Book> getBooksByAuthor(String author);
	public abstract boolean addBook(Book b) throws BookNotFoundException;
	public abstract boolean deleteBook(Book b) throws BookNotFoundException;
	public abstract boolean updateBook(Book b) throws BookNotFoundException;
}
