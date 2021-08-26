package com.bookbros.services;

import java.util.List;

import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.models.Book;
import com.bookbros.models.Purchase;
import com.bookbros.models.User;

public interface PurchaseService {
    Purchase getPurchaseById(int id) throws PurchaseNotFoundException;
    List<Purchase> getAllPurchases();
    List<Purchase> getPurchasesByPurchaser(User purchaser);
    List<Purchase> getPurchasesByBook(Book book);
    boolean addPurchase(Purchase p);
    boolean deletePurchase(Purchase p) throws PurchaseNotFoundException;
    boolean updatePurchase(Purchase p) throws PurchaseNotFoundException;
}
