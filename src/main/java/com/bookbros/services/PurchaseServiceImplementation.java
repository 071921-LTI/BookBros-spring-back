package com.bookbros.services;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookbros.daos.BookRepository;
import com.bookbros.daos.PurchaseRepository;
import com.bookbros.daos.UserRepository;
import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.Purchase;
import com.bookbros.models.User;

@Service
public class PurchaseServiceImplementation implements PurchaseService {
    
    private PurchaseRepository pr;
    private BookRepository br;
    private UserRepository ur;

    @Autowired
    public PurchaseServiceImplementation(PurchaseRepository pr, BookRepository br, UserRepository ur) {
        super();
        this.pr = pr;
        this.br = br;
        this.ur = ur;
    }

    @Override
    @Transactional(readOnly=true)
    public Purchase getPurchaseById(int id) throws PurchaseNotFoundException {
        return pr.findById(id).get();
    }

    @Override
    @Transactional
    public List<Purchase> getAllPurchases() {
        return pr.findAll();
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesByPurchaserId(int id) {
        return pr.findByPurchaserId(id);
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesByBookId(int id) {
        return pr.findByBookId(id);
    }

    @Override
    @Transactional
    public boolean createPurchase(Purchase p) {
        pr.save(p);

        if (pr.findById(p.getId()) == null) {
            return false;
        }
        
        return true;
    }
    
	@Override
	@Transactional
	public Purchase buyBook(String auth, Book book) {
		String[] stringArr = auth.split(":");
		int id = Integer.parseInt(stringArr[0]);
		User user = ur.findById(id).get();
		
//		long millis = System.currentTimeMillis();  
//		java.sql.Date date = new java.sql.Date(millis);
		
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		
		Purchase purchase = new Purchase(user, book, timestamp);
		pr.save(purchase);

		if (pr.findById(purchase.getId()) == null) {
			return null;
		}
		
		book.setInventory(book.getInventory() - 1);
		br.save(book);
		return purchase;
	}

    @Override
    @Transactional
    public boolean deletePurchase(Purchase p) throws PurchaseNotFoundException {
        pr.delete(p);

        if (pr.findById(p.getId()) == null) {
            return false;
        }
        
        return true;
    }

    @Override
    @Transactional
    public boolean updatePurchase(Purchase p) throws PurchaseNotFoundException {
        // TODO Auto-generated method stub
        return false;
    }
}
