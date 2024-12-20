package com.spring.ReactERS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ReactERS.entity.Users;
import com.spring.ReactERS.exception.UniqueEmailException;
import com.spring.ReactERS.service.UserService;

@RestController
public class ReimburstmentController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<Users> createAccount(@RequestBody Users user){
        try{
            Users newUser = userService.addUser(user);
            return ResponseEntity.status(200).body(newUser);
        }catch(UniqueEmailException ex){
            return ResponseEntity.status(409).build();
        }
    }

}
