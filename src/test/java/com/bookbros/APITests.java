package com.bookbros;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.bookbros.apis.BookAPI;

public class APITests {

	@Test
	public void getJsonDataWorks() {
		JSONObject jsonBook =  BookAPI.getJsonData("http://openlibrary.org/api/volumes/brief/isbn/0061120081.json");
		
		String expectedTitle = "To Kill a Mockingbird";
		String actualTitle = "";
		
		try {
			actualTitle = jsonBook.getJSONObject("records")
					.getJSONObject("/books/OL9242848M")
					//.getJSONObject(0)
					.getJSONObject("data")
					.getString("title");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(jsonBook);
		System.out.println(actualTitle);
		assertEquals(expectedTitle, actualTitle);
	}
}
