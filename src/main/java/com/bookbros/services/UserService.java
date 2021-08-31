package com.bookbros.services;

import java.util.List;
import java.util.Optional;

import com.bookbros.exceptions.UserNotFoundException;
import com.bookbros.models.User;

public interface UserService {
	public abstract User getById(int id) throws UserNotFoundException;
	public abstract User getUserByUsername(String username) throws UserNotFoundException;
	public abstract List<User> getUsers();
	public abstract boolean createUser(User u);
	public abstract boolean deleteUser(User u) throws UserNotFoundException;
	public abstract boolean updateUser(User u) throws UserNotFoundException;
}
