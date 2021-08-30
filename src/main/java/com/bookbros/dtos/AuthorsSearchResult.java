package com.bookbros.dtos;

import lombok.Data;

@Data
public class AuthorsSearchResult {
    private Author[] docs;
}
