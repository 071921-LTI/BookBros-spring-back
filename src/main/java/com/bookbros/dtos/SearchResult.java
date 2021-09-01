package com.bookbros.dtos;

import lombok.Data;

@Data
public class SearchResult {
    private int start;
    private int num_found;
    private Work[] docs;
}
