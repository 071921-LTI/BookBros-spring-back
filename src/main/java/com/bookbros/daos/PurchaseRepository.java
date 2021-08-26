package com.bookbros.daos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.bookbros.models.Book;
import com.bookbros.models.Purchase;
import com.bookbros.models.User;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
    public abstract List<Purchase> findByPurchaser(User purchaser);
	public abstract List<Purchase> findByBook(Book book);	
}
