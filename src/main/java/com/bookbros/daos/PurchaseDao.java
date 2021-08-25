package com.bookbros.daos;

import java.util.List;
import com.bookbros.exceptions.PurchaseNotFoundException;
import com.bookbros.models.Purchase;

public interface PurchaseDao {
	public abstract Purchase getPurchaseById(int id) throws PurchaseNotFoundException;
	public abstract List<Purchase> getPurchases();
	public abstract List<Purchase> getPurchasesByUserId(int id);
	public abstract List<Purchase> getPurchasesByBookId(int id);
	public abstract boolean addPurchase(Purchase p) throws PurchaseNotFoundException;
	public abstract boolean deletePurchase(Purchase p) throws PurchaseNotFoundException;
	public abstract boolean updatePurchase(Purchase p) throws PurchaseNotFoundException;
}
