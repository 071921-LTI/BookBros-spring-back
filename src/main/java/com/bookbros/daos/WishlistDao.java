package com.bookbros.daos;

import java.util.List;
import com.bookbros.exceptions.WishlistNotFoundException;
import com.bookbros.models.Wishlist;

public interface WishlistDao {
	public abstract Wishlist getWishById(int id) throws WishlistNotFoundException;
	public abstract List<Wishlist> getWishlist();
	public abstract List<Wishlist> getWishlistsByUserId(int id);
	public abstract List<Wishlist> getWishlistByBookId(int id);
	public abstract boolean addWishlist(Wishlist w) throws WishlistNotFoundException;
	public abstract boolean deleteWishlist(Wishlist w) throws WishlistNotFoundException;
	public abstract boolean updateWishlist(Wishlist w) throws WishlistNotFoundException;
}
