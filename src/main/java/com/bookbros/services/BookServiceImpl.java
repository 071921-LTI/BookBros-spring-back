package com.bookbros.services;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.apis.BookAPI;
import com.bookbros.daos.BookRepository;
import com.bookbros.dtos.Description;
import com.bookbros.dtos.SelectedBook;
import com.bookbros.dtos.Work;
import com.bookbros.models.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository br;
	private BookAPI ba;

	@Autowired
	public BookServiceImpl(BookRepository br, BookAPI ba) {
		super();
		this.br = br;
		this.ba = ba;
	}
	
	@Transactional
	public Book getByAuthorAndTitle(String author, String title) {
		return br.findByAuthorAndTitle(author, title);
	}
	
	@Transactional
	public List<Book> getBooks() {
		return br.findAll();
	}
	
	@Transactional
	public List<Book> getBooksByAuthor(String author) {
		return br.findAllByAuthor(author);
	}
	
	@Transactional
	public boolean createBook(Work work) {

		JSONObject jsonBook = ba.getSelectedBook(work.getKey());
		String description = "";

		System.out.println(jsonBook.get("description").getClass());

		if (jsonBook.get("description").getClass().equals(String.class)) {
			description = jsonBook.getString("description");
		} else {
			try {
				Description descriptionObject = new ObjectMapper().readValue(jsonBook.getJSONObject("description").toString(), Description.class);
				description = descriptionObject.getValue();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String[] subjectsArray = null;
		try {
			subjectsArray = new ObjectMapper().readValue(jsonBook.getJSONArray("subjects").toString(), String[].class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SelectedBook selectedBook = new SelectedBook(jsonBook.getString("title"), description, subjectsArray);

		Book newBook = new Book(selectedBook.getTitle(), work.getAuthor_name()[0], work.getPrice(), String.valueOf(work.getFirst_publish_year()), selectedBook.getDescription(), String.join(", ", selectedBook.getSubjects()), work.getInventory());
//<<<<<<< HEAD
		
		System.out.println(newBook);
		
//=======
//>>>>>>> 844adad631927e11e0cd88d783ce6ef4cf8d1dd4
		br.save(newBook);

		if(br.findById(newBook.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Transactional
	public boolean deleteBook(Book b) {
		br.delete(b);
		if(br.findById(b.getId()) == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateBook(Book b) {
		br.save(b);
		if (br.findByAuthorAndTitle(b.getAuthor(), b.getTitle()) == b) {
			return true;
		} else {
			return false;
		}
	}
}
