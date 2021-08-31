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
}
