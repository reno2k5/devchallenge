package com.studentsco.store.services;


public interface BuyService {

    /**
     * Provides logic for buying a product
     * @param username the username
     * @param productId the product being purchased
     * @param amount the quantity of items being purchased
     * @throws PurchaseException when anything goes wrong: product not found, not enough items in stock
     * @throws UserNotFoundException when the user specified does not exist in records
     */
    void buy(String username, int productId, int amount) throws PurchaseException, UserNotFoundException;
    
}
