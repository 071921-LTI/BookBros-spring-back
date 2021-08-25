package com.bookbros.daos;

import java.util.List;

import com.bookbros.exceptions.RequestNotFoundException;
import com.bookbros.models.Request;

public interface RequestDao {
	public abstract Request getRequestById(int id) throws RequestNotFoundException;
	public abstract List<Request> getRequests();
	public abstract List<Request> getRequestsByUserId(int id);
	public abstract boolean addRequest(Request r) throws RequestNotFoundException;
	public abstract boolean deleteRequest(Request r) throws RequestNotFoundException;
	public abstract boolean updateRequest(Request r) throws RequestNotFoundException;
}
