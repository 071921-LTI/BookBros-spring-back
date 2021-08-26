package com.bookbros.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbros.models.Request;
import com.bookbros.models.User;

public interface RequestRepository extends JpaRepository<Request, Integer>{
	Request findByRequesterAndTitle(User requester, String title);
	List<Request> findRequestsByRequesterId(int id);
	List<Request> findRequestsByTitle(String title);
	List<Request> findRequestsByAuthor(String author);
	List<Request> findRequestsByDescription(String description);
}
