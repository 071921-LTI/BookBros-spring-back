package com.bookbros.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.BookRepository;
import com.bookbros.daos.PurchaseRepository;
import com.bookbros.daos.UserRepository;
import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.Purchase;
import com.bookbros.models.User;
import com.bookbros.services.PurchaseServiceImplementation;

@SpringBootTest(classes= {PurchaseServiceImplementation.class, BookRepository.class, UserRepository.class})
public class PurchaseServiceTest {
	
	@Autowired
	private PurchaseServiceImplementation purchaseService;
	@Autowired
	private BookRepository br;
	@Autowired
	private UserRepository ur;
	
	@MockBean
	private PurchaseRepository mockPurchaseRepository;
	@MockBean
	private BookRepository mockBookRepository;
	@MockBean
	private UserRepository mockUserRepository;
	
	static Purchase mock;
	static Purchase mock2;
	static Optional<Purchase> mockO;
	static Optional<Purchase> mockO2;
	static List<Purchase> mocks; 
	
	@BeforeAll
	public static void setUp(){
		User user = new User();
		Book book = new Book();
		Timestamp timestamp = new Timestamp(0);
		
		Purchase p1 = new Purchase(1, user, book, timestamp);
		Purchase p2 = new Purchase(2, user, book, timestamp);
		
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
		when(mockPurchaseRepository.findById(1)).thenReturn(mockO);
		
		try {
			assertEquals(mock, purchaseService.getPurchaseById(1));
		} catch (PurchaseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByIdInvalid() {
		when(mockPurchaseRepository.findById(5)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(EntityNotFoundException.class, () -> purchaseService.getPurchaseById(5));
	}
	
	@Test
	public void getPurchases() {
		when(mockPurchaseRepository.findAll()).thenReturn(mocks);
		
		assertEquals(mocks, purchaseService.getAllPurchases());
	}
	
	@Test
	public void getPurchasesByPurchaserId() {
		when(mockPurchaseRepository.findByPurchaserId(1)).thenReturn(mocks);
		
		assertEquals(mocks, purchaseService.getPurchasesByPurchaserId(1));
	}
	
	@Test
	public void createPurchaseValid() {
		when(mockPurchaseRepository.findById(1)).thenReturn(mockO);
		
		assertEquals(true, purchaseService.createPurchase(mock));
	}
	
	@Test
	public void createPurchaseInvalid() {
		when(mockPurchaseRepository.findById(mock.getId())).thenReturn(null);
		
		assertEquals(false, purchaseService.createPurchase(mock));
	}
	
	@Test
	public void deletePurchaseValid() {
		when(mockPurchaseRepository.findById(1)).thenReturn(mockO);
		
		try {
			assertEquals(true, purchaseService.deletePurchase(mock));
		} catch (PurchaseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deletePurchaseInvalid() {
		when(mockPurchaseRepository.findById(1)).thenReturn(null);
		
		try {
			assertEquals(false, purchaseService.deletePurchase(mock));
		} catch (PurchaseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updatePurchaseValid() {
		when(mockPurchaseRepository.findByPurchaserId(1)).thenReturn(null);
		
		try {
			assertEquals(false, purchaseService.updatePurchase(mock));
		} catch (PurchaseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void updatePurchaseInvalid() {
		when(mockPurchaseRepository.findByPurchaserId(1)).thenReturn(null);
		
		try {
			assertEquals(false, purchaseService.updatePurchase(mock));
		} catch (PurchaseNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
