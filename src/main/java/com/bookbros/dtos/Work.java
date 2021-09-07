package com.bookbros.dtos;

import lombok.Data;

@Data
public class Work {
    private String key;
    private String title;
    private String[] author_name;
    private int first_publish_year;
    private double price;
	private int inventory;
	
    public Work() {
		super();
	}
	
    public Work(String key, String title, String[] author_name, int first_publish_year, double price, int inventory) {
		super();
		this.key = key;
		this.title = title;
		this.author_name = author_name;
		this.first_publish_year = first_publish_year;
		this.price = price;
		this.inventory = inventory;
	}
}
