package com.bookbros.services;

import java.util.List;

import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.Purchase;

public interface PurchaseService {
    Purchase getPurchaseById(int id) throws PurchaseNotFoundException;
    List<Purchase> getAllPurchases();
    List<Purchase> getPurchasesByPurchaserId(int id);
    List<Purchase> getPurchasesByBookId(int id);
    boolean createPurchase(Purchase p);
    Purchase buyBook(String auth, Book book);
    boolean deletePurchase(Purchase p) throws PurchaseNotFoundException;
    boolean updatePurchase(Purchase p) throws PurchaseNotFoundException;
}
