package com.bookbros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.dtos.Work;
import com.bookbros.models.Book;
import com.bookbros.services.BookService;


@RestController
@RequestMapping("/books")
public class BookController {

	private BookService bs;
	
	@Autowired
	public BookController(BookService bs) {
		super();
		this.bs = bs;
	}

    @GetMapping
	public List<Book> getAllBooks(){
		return bs.getBooks();
    }
	
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody Work work) {
		bs.createBook(work);
        return new ResponseEntity<>("done", HttpStatus.OK);
	}
}
