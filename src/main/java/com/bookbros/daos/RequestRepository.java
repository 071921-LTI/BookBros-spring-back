package com.bookbros.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbros.models.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{
	List<Request> findRequestsByRequesterId(int id);
	List<Request> findRequestsByTitle(String title);
	List<Request> findRequestsByAuthor(String author);
	List<Request> findRequestsByDescription(String description);
}
