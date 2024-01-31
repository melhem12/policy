package com.policy.error;

import org.springframework.http.HttpStatus;

public class DuplicateResultException extends ApiBaseException{
    public DuplicateResultException(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }

}
