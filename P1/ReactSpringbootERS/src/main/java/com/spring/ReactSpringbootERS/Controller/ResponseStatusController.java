package com.spring.ReactSpringbootERS.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spring.ReactSpringbootERS.Exception.IncorrectEmailPasswordException;
import com.spring.ReactSpringbootERS.Exception.PasswordException;
import com.spring.ReactSpringbootERS.Exception.UniqueEmailException;

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

    @ExceptionHandler(PasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public @ResponseBody String wrongPassword(PasswordException ex){
        return ex.getMessage();
    }

}
