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

import com.bookbros.models.User;
import com.bookbros.models.Wishlist;
import com.bookbros.services.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	private WishlistService ws;
	
	@Autowired
	public WishlistController(WishlistService ws) {
		super();
		this.ws = ws;
	}

	@GetMapping
	public ResponseEntity<List<Wishlist>> getWishlist(@RequestHeader("Authorization") String auth) {
		int wisherId = Integer.valueOf(auth.split(":")[0]);
		return new ResponseEntity<>(ws.getWishlistByWisherId(wisherId), HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<String> addWish(@RequestBody Wishlist wish, @RequestHeader("Authorization") String auth) {

		
		int wisherId = Integer.valueOf(auth.split(":")[0]);
		wish.setWisher(new User(wisherId));
		if (ws.addWish(wish)) {
			return new ResponseEntity<>("Successfully added to wishlist", HttpStatus.CREATED);
		}
        return new ResponseEntity<>("Couldn't add to wishlist", HttpStatus.BAD_REQUEST);
	}
}