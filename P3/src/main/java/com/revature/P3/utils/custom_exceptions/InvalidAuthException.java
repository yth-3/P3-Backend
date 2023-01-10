package com.revature.P3.utils.custom_exceptions;

public class InvalidAuthException extends RuntimeException {
    public InvalidAuthException(String message) {
        super(message);
    }
}
