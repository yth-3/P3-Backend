package com.revature.P3.utils.custom_exceptions;

public class InvalidClaimException extends RuntimeException {
    public InvalidClaimException(String message) {
        super(message);
    }
}
