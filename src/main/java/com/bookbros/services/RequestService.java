package com.bookbros.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.daos.RequestRepository;
import com.bookbros.models.Request;
import com.bookbros.models.User;

@Service
public class RequestService {

	private RequestRepository rr;
	
	@Autowired
	public RequestService(RequestRepository rr) {
		super();
		this.rr = rr;
	}
	
	@Transactional
	public Request getRequestById(int id) {
		return rr.findById(id).get();
	}
	
	public List<Request> getRequests() {
		return rr.findAll();
	}
	
	@Transactional
	public List<Request> getRequestsByUserId(int userId) {
		return rr.findRequestsByUserId(userId);
	}
	
	@Transactional
	public boolean createRequest(Request req) {
		rr.save(req);
		if(rr.findById(req.getId()) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	@Transactional
	public boolean deleteRequest(Request req) {
		rr.delete(req);
		// look for req by id in database, if null return true, else return false
		//Request dbReq = getRequestById(req.getId());
		if(rr.findById(req.getId()) == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean updateRequest(Request req) {
		rr.save(req);
		
		//Request dbReq = getRequestById(req.getId());
		if(rr.findRequestsByRequester(req.getRequester()) == req) {
			return true;
		} else {
			return false;
		}
		
		//Request dbReq = rr.findById(id).get();
		
		
		
	}
}
