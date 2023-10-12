package com.policy.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomResponseExceptionHandler {
    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<String> handleCustomResponseException(CustomResponseException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getCustomResponseCode()));
    }
}
