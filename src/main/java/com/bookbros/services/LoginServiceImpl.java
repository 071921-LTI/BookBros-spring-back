package com.bookbros.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookbros.daos.UserRepository;
import com.bookbros.exceptions.UserNotFoundException;
import com.bookbros.models.User;

public class LoginServiceImpl implements LoginService {

	UserRepository ur;
	
    @Autowired
    public LoginServiceImpl(UserRepository ur) {
        super();
        this.ur = ur;
    }

	@Override
	public User login(String username, String password) throws UserNotFoundException {
		User user = ur.findByUsername(username);
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public boolean authorize(String token) throws UserNotFoundException {
	String[] stringArr = token.split(":");
	int id = Integer.parseInt(stringArr[0]);
	String role = stringArr[1];
		User user = ur.getById(id);
		if(!user.getRole().equals(role)) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public User getTokenUser(String token) throws UserNotFoundException {
	String[] stringArr = token.split(":");
	int id = Integer.parseInt(stringArr[0]);
		User user = ur.getById(id);
		return user;
	}
}
