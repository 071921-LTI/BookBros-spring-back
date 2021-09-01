package com.bookbros.dtos;

import lombok.Data;

@Data
public class SelectedBook {
	private String title;
	private String description;
	private String[] subjects;

    public SelectedBook(String title, String description, String[] subjects) {
        super();
        this.title = title;
        this.description = description;
        this.subjects = subjects;
    }
}
