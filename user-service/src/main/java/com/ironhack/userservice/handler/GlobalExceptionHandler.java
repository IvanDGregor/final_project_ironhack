package com.ironhack.userservice.handler;

import com.ironhack.userservice.exceptions.NoSuchUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchUserException.class)
    public void noSuchUserExceptionHandler(NoSuchUserException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
    }
}
