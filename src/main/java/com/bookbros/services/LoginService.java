package com.bookbros.services;

import org.springframework.stereotype.Service;

import com.bookbros.dtos.Credentials;
import com.bookbros.exceptions.UserNotFoundException;
import com.bookbros.models.User;

@Service
public interface LoginService {
	public abstract String login(Credentials cred) throws UserNotFoundException;
	public abstract boolean authorize(String token) throws UserNotFoundException;
	public abstract User getTokenUser (String token) throws UserNotFoundException;
}
