package com.bookbros.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.UserRepository;
import com.bookbros.exceptions.UserNotFoundException;
import com.bookbros.models.User;

@SpringBootTest(classes=UserServiceImpl.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository mockUserRepository;
	
	static List<User> mockUsers; 
	
	static User mockUser;
	
	@BeforeAll
	public static void setUp(){
		User u1 = new User(1, "jimmy", "password", "employee");
        User u2 = new User(2, "johnny", "password", "customer");
        User u3 = new User(3, "jenny", "password", "customer");

        mockUsers = new ArrayList<>();
        mockUsers.add(u1);
        mockUsers.add(u2);
        mockUsers.add(u3);

        mockUser = u1;
	}
	
	@Test
	public void createUserSuccess() {
		when(mockUserRepository.save(new User("testUser", "password", "customer"))).thenReturn(new User(4, "testUser", "password", "customer"));
		
		assertEquals(true, userService.createUser(new User("testUser", "password", "customer")));
	}

    @Test
    public void createUserFail() {
        when(mockUserRepository.save(new User("jimmy", "password", "customer"))).thenReturn(new User(5, "testUser", "password", "customer"));

        assertEquals(false, userService.createUser(new User("jimmy", "password", "customer")));
    }
}
