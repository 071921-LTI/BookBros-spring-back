package com.bookbros.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbros.models.Request;
import com.bookbros.models.User;

public interface RequestRepository extends JpaRepository<Request, Integer>{

	List<Request> findRequestByUserId(int userId);
	List<Request> findRequestByRequester(User requester);
	List<Request> findRequestByTitle(String title);
	List<Request> findRequestByAuthor(String author);
	List<Request> findRequestByDescription(String description);
}
