package com.bookbros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookbros.daos.UserRepository;



@SpringBootApplication
public class BookbrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookbrosApplication.class, args);
	}
}
