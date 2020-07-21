package com.ironhack.edgeservice.exception;

public class OperationsClientNotWorkingException extends RuntimeException {
    /**
     * Throws an Exception if a user client is down.
     * @param message Throws a custom message to the user.
     */
    public OperationsClientNotWorkingException(String message) {
        super(message);
    }
}
