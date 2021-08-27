package com.bookbros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbros.daos.UserRepository;
import com.bookbros.dtos.Credentials;
import com.bookbros.exceptions.UserNotFoundException;
import com.bookbros.models.User;

@Service
public class LoginServiceImpl implements LoginService {

	UserRepository ur;
	
    @Autowired
    public LoginServiceImpl(UserRepository ur) {
        super();
        this.ur = ur;
    }

    @Override
	public String login(Credentials creds) {
		User user = ur.findByUsername(creds.getUsername());
		
		if(user == null || !user.getPassword().equals(creds.getPassword())) {
			return null;
		}
		
		return user.getId()+":"+user.getRole().toString();
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
