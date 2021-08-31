package com.bookbros.services;

import java.util.List;

import com.bookbros.dtos.Work;
import com.bookbros.exceptions.BookNotFoundException;
import com.bookbros.models.Book;

public interface BookService {
	public abstract Book getByAuthorAndTitle(String author, String title) throws BookNotFoundException;
	public abstract List<Book> getBooks();
	public abstract List<Book> getBooksByAuthor(String author);
	public abstract boolean createBook(Work work);
	public abstract boolean deleteBook(Book b) throws BookNotFoundException;
	public abstract boolean updateBook(Book b) throws BookNotFoundException;
}
