package com.spring.ReactERS.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spring.ReactERS.exception.IncorrectEmailPasswordException;
import com.spring.ReactERS.exception.UniqueEmailException;

@ControllerAdvice
public class ResponseStatusController {

    @ExceptionHandler(UniqueEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //409
    public @ResponseBody String duplicateEmail(UniqueEmailException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(IncorrectEmailPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) //401
    public @ResponseBody String wrongEmailPassword(IncorrectEmailPasswordException ex){
        return ex.getMessage();
    }
    
}
