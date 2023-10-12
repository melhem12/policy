package com.policy.exceptionHandler;

public class CustomResponseException extends RuntimeException {

    private int customResponseCode;

    public CustomResponseException(int customResponseCode, String message) {
        super(message);
        this.customResponseCode = customResponseCode;
    }

    public int getCustomResponseCode() {
        return customResponseCode;
    }
}
