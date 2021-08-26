package com.bookbros.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.bookbros.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
    public abstract List<Wishlist> findByWisherId(int id);
	public abstract List<Wishlist> findByBookId(int id);
}