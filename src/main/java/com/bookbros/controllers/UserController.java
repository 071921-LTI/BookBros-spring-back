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
import com.bookbros.models.User;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService us;
	
	@Autowired
	public UserController(UserService us) {
		super();
		this.us = us;
	}

    @GetMapping
    public ResponseEntity<String> firstRoute() {
        return new ResponseEntity<String>("it works!", HttpStatus.OK);
    }

	@GetMapping(value="/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") int id){
        return null;
		
	}
	
	@PostMapping
	public ResponseEntity<String> register(@Valid @RequestBody User user) {
		if (us.createUser(user)) {
			return new ResponseEntity<>("Successfully created user.", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("Username is already taken.", HttpStatus.BAD_REQUEST);
	}
}
