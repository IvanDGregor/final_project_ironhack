package com.ironhack.edgeservice.exception;

public class AccountClientNotWorkingException extends RuntimeException {
    /**
     * Throws an Exception if a user client is down.
     * @param message Throws a custom message to the user.
     */
    public AccountClientNotWorkingException(String message) {
        super(message);
    }
}
