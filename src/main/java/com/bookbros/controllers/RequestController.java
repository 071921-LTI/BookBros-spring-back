package com.bookbros.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.models.Request;
import com.bookbros.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {
	
	private RequestService rs;
	
	@Autowired
	public RequestController(RequestService rs) {
		super();
		this.rs = rs;
	}
	
	public ResponseEntity<String> firstRoute() {
		return new ResponseEntity<String>("it works!", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> submitRequest(@Valid @RequestBody Request request) {
		if (rs.createRequest(request)) {
			return new ResponseEntity<String>("Successfully created request.", HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("Could not make customer request.", HttpStatus.BAD_REQUEST);
	}
}
