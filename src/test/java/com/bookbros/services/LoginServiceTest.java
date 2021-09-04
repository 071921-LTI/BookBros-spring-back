package com.bookbros.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.UserRepository;
import com.bookbros.dtos.Credentials;
import com.bookbros.exceptions.UserNotFoundException;
import com.bookbros.models.User;
import com.bookbros.services.LoginServiceImpl;

@SpringBootTest(classes= {LoginServiceImpl.class})
public class LoginServiceTest {

	User user = new User(1, "newUser", "password", "first");
	User user2 = new User(2, "newUser2", "password2", "first2");
	String token = "1:" + "Customer";
	String token2 = "2:" + "Employee";
	Credentials creds = new Credentials("newUser", "password");
	
	@Autowired
	private LoginServiceImpl loginService;
	
	@MockBean
	private UserRepository mockUserRepository;
	
	@Test
	public void loginSuccess() {
			Mockito.when(mockUserRepository.findByUsername("newUser")).thenReturn(user);
			assertEquals("1:first", loginService.login(creds));
	}
		
		@Test
		public void loginFailure() {
				Mockito.when(mockUserRepository.findByUsername("newUser2")).thenReturn(null);
				assertEquals(null, loginService.login(creds));
	}
		
		@Test
		public void getTokenUserSuccess() {
				Mockito.when(mockUserRepository.getById(1)).thenReturn(user);
				try {
					assertEquals(user, loginService.getTokenUser(token));
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
