package com.bookbros.controllers;

import java.io.PrintWriter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.dtos.Credentials;
import com.bookbros.models.Book;
import com.bookbros.services.BookService;
import com.bookbros.services.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private BookService bs;

	@Autowired
	public BookController(BookServiceImpl bs) {
		super();
		this.bs = bs;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Book> getAllUsers(){
		return bs.getBooks();
	}
}
