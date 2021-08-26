package com.bookbros.services;

import java.util.List;

import com.bookbros.daos.WishlistRepository;
import com.bookbros.exceptions.WishlistNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.User;
import com.bookbros.models.Wishlist;

import org.springframework.beans.factory.annotation.Autowired;

public class WishlistServiceImplementation implements WishlistService {

    private WishlistRepository wr;

    @Autowired
    public WishlistServiceImplementation(WishlistRepository wr) {
        super();
        this.wr = wr;
    }

    @Override
    public Wishlist getWishById(int id) throws WishlistNotFoundException {
        return wr.findById(id).get();
    }

    @Override
    public List<Wishlist> getAllWishes() {
        return wr.findAll();
    }

    @Override
    public List<Wishlist> getWishlistByWisher(User wisher) {
        return wr.findByWisher(wisher);
    }

    @Override
    public List<Wishlist> getWishesByBook(Book book) {
        return wr.findByBook(book);
    }

    @Override
    public boolean addWish(Wishlist w) {
        wr.save(w);

        if (wr.getById(w.getId()) == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteWish(Wishlist w) {
        wr.delete(w);

        if (wr.findById(w.getId()).get() == null) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean updateWish(Wishlist w) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
