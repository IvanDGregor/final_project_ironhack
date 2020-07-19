package com.ironhack.creditcardservice.exception;

public class CreditCardNotFoundException extends RuntimeException {
    /**
     * Throws an Exception if a matching criteria is not met.
     * @param message Throws a custom message to the user.
     */
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}