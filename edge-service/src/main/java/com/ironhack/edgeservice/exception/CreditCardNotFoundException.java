package com.ironhack.edgeservice.exception;

public class CreditCardNotFoundException extends RuntimeException {
    /**
     * Throws an Exception if a user client is down.
     * @param message Throws a custom message to the user.
     */
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}