package com.bookbros.apis;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bookbros.models.Book;
public class BookAPI {
	
	public Book findBookByTitle(String title) {
		String url = "http://openlibrary.org/search.json?title=";
		
		
		RestTemplate rt = new RestTemplate();
		Book b = rt.getForObject(url + title, Book.class);
		
		return b;
	}
	
	public List<Book> findBooksByAuthor(String author) {
		
	}
}
