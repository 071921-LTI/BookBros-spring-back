package com.bookbros.dtos;

import lombok.Data;

@Data
public class CustomerRequest {
	private String custId;
	private String title;
	private String author;
	
	public CustomerRequest(String custId, String title, String author) {
		super();
		this.custId = custId;
		this.title = title;
		this.author = author;
	}
}
