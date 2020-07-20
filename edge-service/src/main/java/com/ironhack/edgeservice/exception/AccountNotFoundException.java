package com.ironhack.edgeservice.exception;

public class AccountNotFoundException extends RuntimeException {
    /**
     * Throws an Exception if a user client is down.
     * @param message Throws a custom message to the user.
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}
