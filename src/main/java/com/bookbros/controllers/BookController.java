package com.bookbros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<List<Book>> getAllBooks(@RequestHeader("Authorization") String auth){

		String role = auth.split(":")[1];

		if (role.equals("Employee")) {
			return new ResponseEntity<>(bs.getBooks(), HttpStatus.OK);
		} else if (role.equals("Customer")) {
			return new ResponseEntity<>(bs.getBooksWithInventory(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(value="/author/{author}")
	public ResponseEntity<List<Book>> getBooksbyAuthor(@RequestHeader("Authorization") String auth, @PathVariable("author") String author){

		String role = auth.split(":")[1];

		if (role.equals("Employee")) {
			return new ResponseEntity<>(bs.findByAuthorContaining(author), HttpStatus.OK);
		} else if (role.equals("Customer")) {
			return new ResponseEntity<>(bs.findByAuthorContainingAndInventoryGreaterThan(author,0), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(value="/title/{title}")
	public ResponseEntity<List<Book>> getBooksbyTitle(@RequestHeader("Authorization") String auth, @PathVariable("title") String title){

		String role = auth.split(":")[1];

		if (role.equals("Employee")) {
			return new ResponseEntity<>(bs.findByTitleContaining(title), HttpStatus.OK);
		} else if (role.equals("Customer")) {
			return new ResponseEntity<>(bs.findByTitleContainingAndInventoryGreaterThan(title,0), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(value="/subject/{subject}")
	public ResponseEntity<List<Book>> getBooksbySubject(@RequestHeader("Authorization") String auth, @PathVariable("subject") String subject){

		String role = auth.split(":")[1];

		if (role.equals("Employee")) {
			return new ResponseEntity<>(bs.findBySubjectsContaining(subject), HttpStatus.OK);
		} else if (role.equals("Customer")) {
			return new ResponseEntity<>(bs.findBySubjectsContainingAndInventoryGreaterThan(subject,0), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
	
	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody Work work) {
		bs.createBook(work);
        return new ResponseEntity<>("done", HttpStatus.OK);
	}
}
