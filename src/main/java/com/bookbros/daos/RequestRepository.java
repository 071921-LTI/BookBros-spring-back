package com.bookbros.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbros.models.Request;
import com.bookbros.models.User;

public interface RequestRepository extends JpaRepository<Request, Integer>{

	List<Request> findRequestsByUserId(int userId);
	List<Request> findRequestsByRequester(User requester);
	List<Request> findRequestsByTitle(String title);
	List<Request> findRequestsByAuthor(String author);
	List<Request> findRequestsByDescription(String description);
}
