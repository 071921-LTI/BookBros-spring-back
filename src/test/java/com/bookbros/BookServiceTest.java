package com.bookbros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.apis.BookAPI;
import com.bookbros.daos.BookRepository;
import com.bookbros.models.Book;
import com.bookbros.services.BookServiceImpl;

@SpringBootTest(classes= {BookServiceImpl.class, BookAPI.class})
public class BookServiceTest {
	
	@Autowired
	private BookServiceImpl bookService;
	
	@MockBean
	private BookRepository mockBookRepository;
	
	static Book mockBook;
	static Book mockBook2;
	static Optional<Book> mockBookO;
	static Optional<Book> mockBookO2;
	static List<Book> mockBooks; 
	
	@BeforeAll
	public static void setUp(){
		Book b1 = new Book(1, "title1", "author1", 1, "date", "description", "subjects", 0);
		Book b2 = new Book(2, "title2", "author2", 2, "date", "description", "subjects", 0);
		
		mockBooks = new ArrayList<>();
		mockBooks.add(b1);
		mockBooks.add(b2);
		
		mockBookO = Optional.of(b1);
		mockBookO2 = Optional.of(b2);
		mockBook = b1;
		mockBook2 = b2;
	}
	
	@Test
	public void getByAuthorAndTitleValid() {
		when(mockBookRepository.findByAuthorAndTitle("title1", "author1")).thenReturn(mockBook);
		
		assertEquals(mockBook, bookService.getByAuthorAndTitle("title1", "author1"));
	}
	
//	@Test
//	public void getByAuthorAndTitleInvalid() {
//		when(mockBookRepository.findByAuthorAndTitle("title3", "author3")).thenReturn(null);
//		
//		assertThrows(null, () -> bookService.getByAuthorAndTitle("title3", "author3"));
//	}
	
	@Test
	public void getBooks() {
		when(mockBookRepository.findAll()).thenReturn(mockBooks);
		
		assertEquals(mockBooks, bookService.getBooks());
	}
	
	@Test
	public void getBooksByAuthor() {
		when(mockBookRepository.findAllByAuthor("author1")).thenReturn(mockBooks);
		
		assertEquals(mockBooks, bookService.getBooksByAuthor("author1"));
	}

}
