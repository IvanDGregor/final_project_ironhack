package com.ironhack.operationsservice.exception;

public class AccountNotFoundException extends RuntimeException {
    /**
     * Throws an Exception if a matching criteria is not met.
     * @param message Throws a custom message to the user.
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}
