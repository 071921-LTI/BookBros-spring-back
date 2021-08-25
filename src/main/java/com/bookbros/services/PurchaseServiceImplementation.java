package com.bookbros.services;

import java.util.List;

import com.bookbros.daos.PurchaseRepository;
import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.models.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
    public List<Purchase> getPurchasesByUserId(int id) {
        return pr.findByUserId(id);
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesByBookId(int id) {
        return pr.findByBookId(id);
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
