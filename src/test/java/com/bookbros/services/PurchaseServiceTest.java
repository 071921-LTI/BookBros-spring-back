package com.bookbros.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.PurchaseRepository;
import com.bookbros.models.Book;
import com.bookbros.models.Purchase;
import com.bookbros.models.User;
import com.bookbros.services.PurchaseServiceImplementation;

@SpringBootTest(classes= {PurchaseServiceImplementation.class})
public class PurchaseServiceTest {
	
	@Autowired
	private PurchaseServiceImplementation purchaseService;
	
	@MockBean
	private PurchaseRepository mockPurchaseRepository;
	
	static Purchase mockPurchase;
	static Purchase mockPurchase2;
	static Optional<Purchase> mockPurchaseO;
	static Optional<Purchase> mockPurchaseO2;
	static List<Purchase> mockPurchases; 
	
	@BeforeAll
	public static void setUp(){
		User user = new User();
		Book book = new Book();
		Timestamp timestamp = new Timestamp(0);
		
		Purchase p1 = new Purchase(1, user, book, timestamp);
		Purchase p2 = new Purchase(2, user, book, timestamp);
		
		mockPurchases = new ArrayList<>();
		mockPurchases.add(p1);
		mockPurchases.add(p2);
		
		mockPurchaseO = Optional.of(p1);
		mockPurchaseO2 = Optional.of(p2);
		mockPurchase = p1;
		mockPurchase2 = p2;
	}
}
