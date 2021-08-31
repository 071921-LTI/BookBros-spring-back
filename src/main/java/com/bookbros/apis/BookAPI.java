package com.bookbros.apis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bookbros.dtos.SearchResult;
import com.bookbros.dtos.SelectedBook;
import com.bookbros.models.Book;

import org.json.*;
//import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class BookAPI {
	

//	public static Book findBookByTitle(String title) {
//		String titleUrl = "http://openlibrary.org/search.json?title=" + title;
//		
//		// get a jsonobject from the serch-by-title API
//		JSONObject titleJsonOjbect =  getJsonData(titleUrl);
//		
//		// get the book ISBN from the book-title API call
//		JSONArray docsArray = titleJsonOjbect
//				.getJSONArray("docs");
//		
//		JSONObject firstObj = docsArray.getJSONObject(0);
//		
////		String isbn = titleJsonOjbect
////				.getJSONArray("docs")
////				.getJSONArray("isbn")
//				
//						
//				
//				
//				
//		
//		// put the ISBN in the ISBN API call
//		String isbnUrl = "http://openlibrary.org/api/volumes/brief/isbn/" + isbn + ".json";
//		
//		// get the book info and convert it into a java Book object 
//		String jsonBookString = "";
//		JSONObject jsonBook = new JSONObject();
//		
//		String bookTitle;
//		String bookAuthor;
//		String bookPublished;
//		String bookDescription;
//		String bookSubjects;
//		
//		
//		
////		RestTemplate rt = new RestTemplate();
////		Book b = rt.getForObject(url + title, Book.class);
////		
////		return b;
//	}
	

	public static JSONObject getJsonData(String jsonUrl) {
		
		JSONObject data_obj = null;
		
		try {
			URL url = new URL(jsonUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responsecode = conn.getResponseCode();
            
            String inline = "";
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            }  else {
            	
            	
                Scanner scanner = new Scanner(url.openStream());
            	
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
            	
                scanner.close();
                
            }
            
//            JSONParser parse = new JSONParser();
//            JSONObject data_obj = (JSONObject) parse.parse(inline);
            	data_obj = new JSONObject(inline);
            
            
            
		} catch (Exception e) {
            e.printStackTrace();
        }
		return data_obj;
	}

	public SearchResult searchAuthors(String authorName) {
		
		String url = "https://openlibrary.org/search.json?author=" + authorName;

		RestTemplate rt = new RestTemplate();
		ResponseEntity<SearchResult> response =
					rt.getForEntity(
					url,
					SearchResult.class);
		SearchResult result = response.getBody();

		return result;
	
	}

	public SelectedBook getSelectedBook(String key) {
		String url = "https://openlibrary.org/" + key + ".json";

		RestTemplate rt = new RestTemplate();
		SelectedBook book = rt.getForObject(url, SelectedBook.class);

		return book;
	}
	
}
