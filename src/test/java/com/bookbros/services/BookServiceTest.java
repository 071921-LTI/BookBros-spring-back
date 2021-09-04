package com.bookbros.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookbros.apis.BookAPI;
import com.bookbros.daos.BookRepository;
import com.bookbros.dtos.SelectedBook;
import com.bookbros.dtos.Work;
import com.bookbros.models.Book;
import com.bookbros.services.BookServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes= {BookServiceImpl.class, BookAPI.class})
public class BookServiceTest {
	
	@Autowired
	private BookServiceImpl bookService;
	@Autowired
	private BookAPI bp;
	
	@MockBean
	private BookRepository mockBookRepository;
	@MockBean
	private BookAPI mockApi;
	
	ObjectMapper mapper = new ObjectMapper();
	
	static Book mockBook;
	static Book mockBook2;
	static Work mockW;
	static SelectedBook mockS;
	static Optional<Book> mockBookO;
	static Optional<Book> mockBookO2;
	static List<Book> mockBooks; 
	
	@BeforeAll
	public static void setUp(){
		Book b1 = new Book(1, "title1", "author1", 1, "date", "description", "subjects", 0);
		Book b2 = new Book(2, "title2", "author2", 2, "date", "description", "subjects", 0);
		String[] array = new String[] {"one", "two"};
		
		SelectedBook s = new SelectedBook("title", "description", array);
		Work w = new Work("key","title", array, 1111, 20.0, 1);
		
		mockBooks = new ArrayList<>();
		mockBooks.add(b1);
		mockBooks.add(b2);
		
		mockBookO = Optional.of(b1);
		mockBookO2 = Optional.of(b2);
		mockBook = b1;
		mockBook2 = b2;
		
		mockW = w;
		mockS = s;
	}
	
	@Test
	public void createBookValid() {
		
		//Creates JSON object from selected book
		JSONObject mJSONObject = null;
		try {
			String jsonInString = mapper.writeValueAsString(mockS);
			mJSONObject = new JSONObject(jsonInString);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		when(mockApi.getSelectedBook(mockW.getKey())).thenReturn(mJSONObject);
		
		assertEquals(true, bookService.createBook(mockW));
	}
	
	@Test
	public void createBookInvalid() {
		
		//Creates JSON object from selected book
		JSONObject mJSONObject = null;
		try {
			String jsonInString = mapper.writeValueAsString(mockS);
			mJSONObject = new JSONObject(jsonInString);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		when(mockApi.getSelectedBook(mockW.getKey())).thenReturn(mJSONObject);
		when(mockBookRepository.findById(0)).thenReturn(null);
		
		assertEquals(false, bookService.createBook(mockW));
	}
	
	@Test
	public void getByAuthorAndTitleValid() {
		when(mockBookRepository.findByAuthorAndTitle("title1", "author1")).thenReturn(mockBook);
		
		assertEquals(mockBook, bookService.getByAuthorAndTitle("title1", "author1"));
	}
	
	@Test
	public void getByAuthorAndTitleInvalid() {
		when(mockBookRepository.findByAuthorAndTitle("author3", "title3")).thenReturn(null);
		
		assertEquals(null, bookService.getByAuthorAndTitle("author3", "title3"));
	}
	
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
	
	@Test
	public void deleteBookValid() {
		when(mockBookRepository.findById(1)).thenReturn(null);
		
		assertEquals(true, bookService.deleteBook(mockBook));
	}
	
	@Test
	public void deleteBookInvalid() {
		when(mockBookRepository.findById(1)).thenReturn(mockBookO);
		
		assertEquals(false, bookService.deleteBook(mockBook));
	}
	
	@Test
	public void updateBookValid() {
		when(mockBookRepository.findByAuthorAndTitle(mockBook.getAuthor(), mockBook.getTitle())).thenReturn(mockBook);
		
		assertEquals(true, bookService.updateBook(mockBook));
	}
	
	@Test
	public void updateBookInvalid() {
		when(mockBookRepository.findByAuthorAndTitle("author1", "title1")).thenReturn(null);
		
		assertEquals(false, bookService.updateBook(mockBook));
	}
	
	@Test
	public void getBooksWithInventory() {
		when(mockBookRepository.findByInventoryGreaterThan(0)).thenReturn(mockBooks);
		
		assertEquals(mockBooks, bookService.getBooksWithInventory());
	}
	
	@Test
	public void findByTitleContaining() {
		when(mockBookRepository.findByTitleContaining("title")).thenReturn(mockBooks);
		
		assertEquals(mockBooks, bookService.findByTitleContaining("title"));
	}
	
	@Test
	public void findByTitleContainingAndInventoryGreaterThan() {
		when(mockBookRepository.findByTitleContainingAndInventoryGreaterThan("author1", 0)).thenReturn(mockBooks);
		
		assertEquals(mockBooks, bookService.findByTitleContainingAndInventoryGreaterThan("author1", 0));
	}
	
	@Test
	public void findByAuthorContaining() {
		when(mockBookRepository.findByAuthorContaining("author1")).thenReturn(mockBooks);
		
		assertEquals(mockBooks, bookService.findByAuthorContaining("author1"));
	}
	
	@Test
	public void findByAuthorContainingAndInventoryGreaterThan() {
		when(mockBookRepository.findByAuthorContainingAndInventoryGreaterThan("author1", 0)).thenReturn(mockBooks);
		
		assertEquals(mockBooks, bookService.findByAuthorContainingAndInventoryGreaterThan("author1", 0));
	}

}
