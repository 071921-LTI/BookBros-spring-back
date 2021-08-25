package com.bookbros.daos;

import java.util.List;

import com.bookbros.models.User;
import com.bookbros.exceptions.UserNotFoundException;

public interface UserDao {
	public abstract User getUserById(int id) throws UserNotFoundException;
	public abstract User getUserByUsername(String username) throws UserNotFoundException;
	public abstract List<User> getUsers();
	public abstract boolean addUser(User u) throws UserNotFoundException;
	public abstract boolean deleteUser(User u) throws UserNotFoundException;
	public abstract boolean updateUser(User u) throws UserNotFoundException;
}
