package com.bookbros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.apis.BookAPI;
import com.bookbros.dtos.SearchResult;

@RestController
@RequestMapping("/api")
public class APIController {

	private BookAPI bookApi;
	
	@Autowired
	public APIController(BookAPI bookApi) {
		super();
		this.bookApi = bookApi;
	}

    @GetMapping(value="/authors/{authorName}")
    public ResponseEntity<SearchResult> searchAuthors(@PathVariable("authorName") String authorName) {
        return new ResponseEntity<>(bookApi.searchAuthors(authorName), HttpStatus.OK);
    }
    
    @GetMapping(value="/title/{title}")
    public ResponseEntity<SearchResult> searchTitle(@PathVariable("title") String title) {
    	return new ResponseEntity<>(bookApi.searchTitle(title), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SearchResult> searchAuthorAndTitle(@RequestParam("title") String title, @RequestParam("author") String author) {
        return new ResponseEntity<>(bookApi.searchAuthorAndTitle(title, author), HttpStatus.OK);
    }
}