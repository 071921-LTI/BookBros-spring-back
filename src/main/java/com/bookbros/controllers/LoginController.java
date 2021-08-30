package com.bookbros.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.services.LoginService;
import com.bookbros.services.LoginServiceImpl;
import com.bookbros.dtos.Credentials;
import com.bookbros.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private LoginService lg;

	@Autowired
	public LoginController(LoginServiceImpl lg) {
		super();
		this.lg = lg;
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@Valid @RequestBody Credentials creds) {
		String token = "";
		try {
			token = lg.login(creds);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(token == null) {
			return new ResponseEntity<>("Invalid attempt.", HttpStatus.UNAUTHORIZED);
		}
		HttpHeaders hh = new HttpHeaders();
		hh.set("Authorization", token);
		return new ResponseEntity<>(hh, HttpStatus.OK);
	}
}
