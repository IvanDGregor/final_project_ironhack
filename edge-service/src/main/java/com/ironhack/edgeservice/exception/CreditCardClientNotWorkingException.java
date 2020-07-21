package com.ironhack.edgeservice.exception;

public class CreditCardClientNotWorkingException extends RuntimeException {
    /**
     * Throws an Exception if a user client is down.
     * @param message Throws a custom message to the user.
     */
    public CreditCardClientNotWorkingException(String message) {
        super(message);
    }
}
