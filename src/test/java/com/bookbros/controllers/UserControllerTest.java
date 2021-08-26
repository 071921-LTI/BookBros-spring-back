package com.bookbros.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bookbros.models.User;
import com.bookbros.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@MockBean
	private UserService mockUserService;
	
	@Autowired
	private MockMvc mockMvc;
	
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
    public void registerSuccess() throws Exception {
        when(mockUserService.createUser(new User("testUser", "password", "customer"))).thenReturn(true);

        mockMvc.perform(post("/users")
                .content(convertToJson(new User("testUser", "password", "customer")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("Successfully created user."));
    }

    @Test
    public void registerFail() throws Exception {
        when(mockUserService.createUser(new User("testUser", "password", "customer"))).thenReturn(false);

        mockMvc.perform(post("/users")
                .content(convertToJson(new User("testUser", "password", "customer")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Username is already taken."));
    }

    public static String convertToJson(User user) {
		try {
			return new ObjectMapper().writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}
	
}