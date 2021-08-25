package com.bookbros.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookbros.models.Book;
import com.bookbros.models.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	Book findByAuthorAndTitle(String author, String title);
	List<Book> findAllByAuthor(String author);
}
