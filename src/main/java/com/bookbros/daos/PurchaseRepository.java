package com.bookbros.daos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.bookbros.models.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{
    public abstract List<Purchase> findByPurchaserId(int id);
	public abstract List<Purchase> findByBookId(int id);	
}
