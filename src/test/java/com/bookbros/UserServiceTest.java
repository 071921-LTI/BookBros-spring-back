  
package com.bookbros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.UserRepository;
import com.bookbros.models.User;
import com.bookbros.services.UserServiceImpl;

@SpringBootTest(classes=UserServiceImpl.class)
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userService;
	
	@MockBean
	private UserRepository mockUserRepository;
	
	static User mockUser;
	static User mockUser2;
	static Optional<User> mockUserO;
	static Optional<User> mockUserO2;
	static List<User> mockUsers; 
	
	@BeforeAll
	public static void setUp(){
		User u1 = new User(1, "Tommy", null, null);
		User u2 = new User(2, "James", null, null);
		
		mockUsers = new ArrayList<>();
		mockUsers.add(u1);
		mockUsers.add(u2);
		
		mockUserO = Optional.of(u1);
		mockUserO2 = Optional.of(u2);
		mockUser = u1;
		mockUser2 = u2;
	}
	
	@Test
	public void getByIdValid() {
		when(mockUserRepository.findById(1)).thenReturn(mockUserO);
		
		assertEquals(mockUser, userService.getById(1));
	}
	
	@Test
	public void getByIdInvalid() {
		when(mockUserRepository.findById(5)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(EntityNotFoundException.class, () -> userService.getById(5));
	}
	
	@Test
	public void getByUsernameValid() {
		when(mockUserRepository.findByUsername("Tommy")).thenReturn(mockUser);
		
		assertEquals(mockUser, userService.getUserByUsername("Tommy"));
	}
	
	@Test
	public void getByUsernameInvalid() {
		when(mockUserRepository.findByUsername("Bobby")).thenThrow(EntityNotFoundException.class);
		
		assertThrows(EntityNotFoundException.class, () -> userService.getUserByUsername("Bobby"));
	}
	
	@Test
	public void getUsers() {
		when(mockUserRepository.findAll()).thenReturn(mockUsers);
		
		assertEquals(mockUsers, userService.getUsers());
	}
	
	@Test
	public void createUserValid() {
		when(mockUserRepository.findById(1)).thenReturn(mockUserO);
		
		assertEquals(true, userService.createUser(mockUser));
	}
	
	@Test
	public void createUserInvalid() {
		when(mockUserRepository.findById(mockUser.getId())).thenReturn(null);
		
		assertEquals(false, userService.createUser(mockUser));
	}
	
	@Test
	public void deleteUserValid() {
		when(mockUserRepository.findById(1)).thenReturn(null);
		
		assertEquals(true, userService.deleteUser(mockUser));
	}
	
	@Test
	public void deleteUserInvalid() {
		when(mockUserRepository.findById(1)).thenReturn(mockUserO);
		
		assertEquals(false, userService.deleteUser(mockUser));
	}
	
	@Test
	public void updateUserValid() {
		when(mockUserRepository.findByUsername("Tommy")).thenReturn(mockUser);
		
		assertEquals(true, userService.updateUser(mockUser));
	}
	
	@Test
	public void updateUserInvalid() {
		when(mockUserRepository.findByUsername("Tommy")).thenReturn(null);
		
		assertEquals(false, userService.updateUser(mockUser));
	}
}
