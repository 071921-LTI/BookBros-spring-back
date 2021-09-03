package com.bookbros.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookbros.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	Book findByAuthorAndTitle(String author, String title);
	List<Book> findAllByAuthor(String author);
	List<Book> findByInventoryGreaterThan(int inventory);
	List<Book> findByTitleContaining(String title);
	List<Book> findByTitleContainingAndInventoryGreaterThan(String title, int inventory);
	List<Book> findByAuthorContaining(String author);
	List<Book> findByAuthorContainingAndInventoryGreaterThan(String author, int inventory);
}
