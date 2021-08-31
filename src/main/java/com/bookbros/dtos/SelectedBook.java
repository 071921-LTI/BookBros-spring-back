package com.bookbros.dtos;

import lombok.Data;

@Data
public class SelectedBook {
	private String title;
	private String description;
	private String[] subjects;
}
