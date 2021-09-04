package com.bookbros.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.daos.WishlistRepository;
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

		mock = p1;
		mock2 = p2;
	}

}