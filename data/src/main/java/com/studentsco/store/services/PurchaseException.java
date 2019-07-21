package com.studentsco.store.services;

public class PurchaseException extends Exception {

    public PurchaseException(String message) {
        super(message);
    }
    
    public PurchaseException(String message, Throwable cause){
        super(message, cause);
    }
}
