package com.bookbros.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.daos.RequestRepository;
import com.bookbros.dtos.Work;
import com.bookbros.models.Request;

@Service
public class RequestService {

	private RequestRepository rr;
	private BookService bs;
	
	@Autowired
	public RequestService(RequestRepository rr, BookService bs) {
		super();
		this.rr = rr;
		this.bs = bs;
	}
	
	@Transactional
	public Request getRequestById(int id) {
		return rr.findById(id).get();
	}
	
	public List<Request> getRequests() {
		return rr.findAll();
	}
	
	@Transactional
	public List<Request> getRequestsByRequesterId(int id) {
		return rr.findRequestsByRequesterId(id);
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
	public boolean approveRequest(int requestId, Work work) {

		if (bs.createBook(work)) {
			rr.deleteById(requestId);
			if (rr.findById(requestId) == null) {
				return true;
			}
		}
		return false;
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
		if(rr.findByRequesterAndTitle(req.getRequester(), req.getTitle()) == req) {
			return true;
		} else {
			return false;
		}
		
		//Request dbReq = rr.findById(id).get();
		
		
		
	}
}
