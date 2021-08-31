package com.bookbros.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.services.BookService;
import com.bookbros.apis.BookAPI;
import com.bookbros.dtos.SelectedBook;
import com.bookbros.dtos.Work;
import com.bookbros.models.Book;
import com.bookbros.models.User;

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
    public ResponseEntity<String> firstRoute() {
        return new ResponseEntity<String>("it works!", HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody Work work) {
		boolean addedBook = bs.createBook(work);
        return new ResponseEntity<>("done", HttpStatus.OK);
	}
}
