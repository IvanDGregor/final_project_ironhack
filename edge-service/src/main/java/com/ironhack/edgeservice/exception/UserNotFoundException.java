package com.ironhack.edgeservice.exception;

public class UserNotFoundException extends RuntimeException {
    /**
     * Throws an Exception if a user client is down.
     * @param message Throws a custom message to the user.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}