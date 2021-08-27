package com.bookbros.services;

import com.bookbros.exceptions.UserNotFoundException;
import com.bookbros.models.User;

public interface LoginService {
	User login(String username, String password) throws UserNotFoundException;
	boolean authorize(String token) throws UserNotFoundException;
	User getTokenUser (String token) throws UserNotFoundException;
}
