package com.bookbros.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.WishlistRepository;
import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.exceptions.WishlistNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.User;
import com.bookbros.models.Wishlist;
import com.bookbros.services.WishlistServiceImplementation;


@SpringBootTest(classes= {WishlistServiceImplementation.class})
public class WishlistServiceTest {

	@Autowired
	private WishlistServiceImplementation wishlistService;

	@MockBean
	private WishlistRepository mockWishlistRepository;

	static Wishlist mock;
	static Wishlist mock2;
	static Optional<Wishlist> mockO;
	static Optional<Wishlist> mockO2;
	static List<Wishlist> mocks; 

	@BeforeAll
	public static void setUp(){
		User user = new User();
		Book book = new Book();
		LocalDate localDate = null;

		Wishlist p1 = new Wishlist(1, user, book, localDate);
		Wishlist p2 = new Wishlist(2, user, book, localDate);

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
		when(mockWishlistRepository.findById(1)).thenReturn(mockO);
		try {
			assertEquals(mock, wishlistService.getWishById(1));
		} catch (WishlistNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByIdInvalid() {
		when(mockWishlistRepository.findById(5)).thenThrow(EntityNotFoundException.class);
		
		assertThrows(EntityNotFoundException.class, () -> wishlistService.getWishById(5));
	}
	
	@Test
	public void getAllWishes() {
		when(mockWishlistRepository.findAll()).thenReturn(mocks);
		
		assertEquals(mocks, wishlistService.getAllWishes());
	}
	
	@Test
	public void getWishlistByWisherId() {
		when(mockWishlistRepository.findByWisherId(1)).thenReturn(mocks);
		
		assertEquals(mocks, wishlistService.getWishlistByWisherId(1));
	}
	
	@Test
	public void getWishesByBookId() {
		when(mockWishlistRepository.findByBookId(1)).thenReturn(mocks);
		
		assertEquals(mocks, wishlistService.getWishesByBookId(1));
	}
	
	@Test
	public void addWishValid() {
		when(mockWishlistRepository.findById(1)).thenReturn(mockO);
		
		assertEquals(true, wishlistService.addWish(mock));
	}
	
	@Test
	public void addWishInvalid() {
		when(mockWishlistRepository.findById(mock.getId())).thenReturn(null);
		
		assertEquals(false, wishlistService.addWish(mock));
	}
	
	@Test
	public void deleteWishValid() {
		when(mockWishlistRepository.findById(1)).thenReturn(mockO);
		assertEquals(true, wishlistService.deleteWish(mock));
	}
	
	@Test
	public void deleteWishInvalid() {
		when(mockWishlistRepository.findById(1)).thenReturn(null);
		assertEquals(false, wishlistService.deleteWish(mock));
	}
	
	@Test
	public void updateWishValid() {
		when(mockWishlistRepository.findByWisherId(1)).thenReturn(null);
		assertEquals(false, wishlistService.updateWish(mock));
	}
	
	@Test
	public void updateWishInvalid() {
		when(mockWishlistRepository.findByWisherId(1)).thenReturn(null);
		assertEquals(false, wishlistService.updateWish(mock));
	}
}