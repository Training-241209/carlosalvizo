package com.spring.ReactSpringbootERS.Exception;

public class UniqueEmailException extends RuntimeException {
    
    public UniqueEmailException(String message) {
        super(message);
    }
}
