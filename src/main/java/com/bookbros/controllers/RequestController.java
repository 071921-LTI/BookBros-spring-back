package com.bookbros.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.models.Request;
import com.bookbros.models.User;
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
	
//	@GetMapping
//	public ResponseEntity<String> firstRoute() {
//		return new ResponseEntity<String>("it works!", HttpStatus.OK);
//	}
	
	@GetMapping
	public ResponseEntity<List<Request>> getRequests() {
		return new ResponseEntity<List<Request>>(rs.getRequests(), HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<String> addRequest(@RequestBody Request request, @RequestHeader("Authorization") String auth) {

		int requesterId = Integer.valueOf(auth.split(":")[0]);
		request.setRequester(new User(requesterId));
		if (rs.createRequest(request)) {
			return new ResponseEntity<>("Successfully created customer request.", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Could not make customer request.", HttpStatus.BAD_REQUEST);
		
	}
}
