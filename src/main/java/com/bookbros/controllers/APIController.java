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

import com.bookbros.services.UserService;
import com.bookbros.apis.BookAPI;
import com.bookbros.dtos.Author;
import com.bookbros.dtos.AuthorsSearchResult;
import com.bookbros.models.User;

@RestController
@RequestMapping("/api")
public class APIController {

	private BookAPI bookApi;
	
	@Autowired
	public APIController(BookAPI bookApi) {
		super();
		this.bookApi = bookApi;
	}

    @GetMapping(value="/authors")
    public ResponseEntity<AuthorsSearchResult> searchAuthors(@RequestBody Author author) {
        return new ResponseEntity<>(bookApi.searchAuthors(author.getName()), HttpStatus.OK);
    }
}