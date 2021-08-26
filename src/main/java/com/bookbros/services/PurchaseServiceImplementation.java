package com.bookbros.services;

import java.util.List;

import com.bookbros.daos.PurchaseRepository;
import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.Purchase;
import com.bookbros.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PurchaseServiceImplementation implements PurchaseService {
    
    private PurchaseRepository pr;

    @Autowired
    public PurchaseServiceImplementation(PurchaseRepository pr) {
        super();
        this.pr = pr;
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
    public List<Purchase> getPurchasesByPurchaser(User purchaser) {
        return pr.findByPurchaser(purchaser);
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesByBook(Book book) {
        return pr.findByBook(book);
    }

    @Override
    @Transactional
    public boolean addPurchase(Purchase p) {
        pr.save(p);

        if (pr.getById(p.getId()) == null) {
            return false;
        }
        
        return true;
    }

    @Override
    @Transactional
    public boolean deletePurchase(Purchase p) throws PurchaseNotFoundException {
        pr.delete(p);

        if (pr.findById(p.getId()).get() == null) {
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
