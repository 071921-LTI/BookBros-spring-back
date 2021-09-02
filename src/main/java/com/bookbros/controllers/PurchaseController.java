package com.bookbros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbros.models.Book;
import com.bookbros.models.Purchase;
import com.bookbros.services.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	private PurchaseService ps;
	
	@Autowired
	public PurchaseController(PurchaseService ps) {
		super();
		this.ps = ps;
	}

	@GetMapping
	public List<Purchase> getPurchases() {
		return ps.getAllPurchases();
    }
	
	@GetMapping(value="/{id}")
	public List<Purchase> getPurchasesById(@PathVariable("id") int id) {
		return ps.getPurchasesByPurchaserId(id);
    }
	
//	@PostMapping
//	@RequestMapping("/displayHeaderInfo.do")
//	public ResponseEntity<String> addPurchase(@RequestHeader("Authorization") String auth, @RequestBody Book book) {
//		Purchase purchase = ps.buyBook(auth, book);
//		return new ResponseEntity<>("added purchase " + purchase.getId(), HttpStatus.CREATED);
//	}
	
//	@PostMapping
//	public ResponseEntity<String> register(@Valid @RequestBody User user) {
//		if (us.createUser(user)) {
//			return new ResponseEntity<>("Successfully created user.", HttpStatus.CREATED);
//		}
//
//		return new ResponseEntity<String>("Username is already taken.", HttpStatus.BAD_REQUEST);
//	}
}
