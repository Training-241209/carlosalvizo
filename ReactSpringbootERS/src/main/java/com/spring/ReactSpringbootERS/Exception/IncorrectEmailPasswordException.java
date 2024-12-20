package com.spring.ReactSpringbootERS.Exception;

public class IncorrectEmailPasswordException extends RuntimeException {
    
    public IncorrectEmailPasswordException(String message) {
        super(message);
    }
}
