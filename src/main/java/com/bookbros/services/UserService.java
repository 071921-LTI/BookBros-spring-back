package com.bookbros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.daos.UserRepository;
import com.bookbros.models.User;

@Service
public class UserService {
	
	private UserRepository ur;

	@Autowired
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	@Transactional
	public User getUserByUsername(String username) {
		return ur.findByUsername(username);
	}
	
	@Transactional
	public List<User> getUsers() {
		return ur.findAll();
	}
	
	@Transactional
	public boolean createUser(User u) {
		ur.save(u);

		if(ur.findById(u.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Transactional
	public boolean deleteUser(User u) {
		ur.delete(u);
		if(ur.findById(u.getId()) == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateUser(User u) {
		ur.save(u);
		if (ur.findByUsername(u.getUsername()) == u) {
			return true;
		} else {
			return false;
		}
	}
}
