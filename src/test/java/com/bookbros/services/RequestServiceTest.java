package com.bookbros.services;

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
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.daos.RequestRepository;
import com.bookbros.models.Book;
import com.bookbros.models.Request;
import com.bookbros.models.User;
import com.bookbros.services.RequestService;


@SpringBootTest(classes= {RequestService.class})
public class RequestServiceTest {

	@Autowired
	private RequestService requestService;

	@MockBean
	private RequestRepository mockRequestRepository;

	static Request mock;
	static Request mock2;
	static Optional<Request> mockO;
	static Optional<Request> mockO2;
	static List<Request> mocks; 

	@BeforeAll
	public static void setUp(){
		User user = new User();

		Request p1 = new Request(1, user, "title", "author", "description");
		Request p2 = new Request(2, user, "title", "author", "description");

		mocks = new ArrayList<>();
		mocks.add(p1);
		mocks.add(p2);

		mockO = Optional.of(p1);
		mockO2 = Optional.of(p2);
		mock = p1;
		mock2 = p2;
	}
	
	@Test
	public void getByIdValid() {
		when(mockRequestRepository.findById(1)).thenReturn(mockO);
		
		assertEquals(mock, requestService.getRequestById(1));
	}
	
	@Test
	public void getByIdInvalid() {
		when(mockRequestRepository.findById(5)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(EntityNotFoundException.class, () -> requestService.getRequestById(5));
	}
	
	@Test
	public void getRequests() {
		when(mockRequestRepository.findAll()).thenReturn(mocks);
		
		assertEquals(mocks, requestService.getRequests());
	}
	
	@Test
	public void getRequestsByRequesterId() {
		when(mockRequestRepository.findRequestsByRequesterId(1)).thenReturn(mocks);
		
		assertEquals(mocks, requestService.getRequestsByRequesterId(1));
	}
	
	@Test
	public void createRequestValid() {
		when(mockRequestRepository.findById(1)).thenReturn(mockO);
		
		assertEquals(true, requestService.createRequest(mock));
	}
	
	@Test
	public void createRequestInvalid() {
		when(mockRequestRepository.findById(mock.getId())).thenReturn(null);
		
		assertEquals(false, requestService.createRequest(mock));
	}
	
	@Test
	public void deleteRequestValid() {
		when(mockRequestRepository.findById(1)).thenReturn(null);
		
		assertEquals(true, requestService.deleteRequest(mock));
	}
	
	@Test
	public void deleteRequestInvalid() {
		when(mockRequestRepository.findById(1)).thenReturn(mockO);
		
		assertEquals(false, requestService.deleteRequest(mock));
	}
	
	@Test
	public void updateRequestValid() {
		when(mockRequestRepository.findRequestsByRequesterId(1)).thenReturn(null);
		
		assertEquals(false, requestService.updateRequest(mock));
	}
	
	@Test
	public void updateRequestInvalid() {
		when(mockRequestRepository.findRequestsByRequesterId(1)).thenReturn(null);
		
		assertEquals(false, requestService.updateRequest(mock));
	}
}
