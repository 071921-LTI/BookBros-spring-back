package com.bookbros.services;

import java.util.List;

import com.bookbros.exceptions.WishlistNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.User;
import com.bookbros.models.Wishlist;

public interface WishlistService {
    Wishlist getWishById(int id) throws WishlistNotFoundException;
    List<Wishlist> getAllWishes();
    List<Wishlist> getWishlistByWisher(User wisher);
    List<Wishlist> getWishesByBook(Book book);
    boolean addWish(Wishlist w);
    boolean deleteWish(Wishlist w);
    boolean updateWish(Wishlist w);
}
