package com.sirma.employees.core.collaborations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DateFormatterException.class)
    public ResponseEntity<ErrorResponseWrapper> handleBadRequest(AccountNotFoundException ex) {
        ErrorResponseWrapper error = new ErrorResponseWrapper(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis(),
                null
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}