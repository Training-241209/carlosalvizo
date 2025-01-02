package com.spring.ReactSpringbootERS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.spring.ReactSpringbootERS.DTOs.UsersDTO;

import com.spring.ReactSpringbootERS.Service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    private UserService userService;

@GetMapping
public ResponseEntity<List<UsersDTO>> managerGetAllUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
    String token = authorizationHeader.substring(7);
    try {
        List<UsersDTO> usersDTO = userService.managerGetUsers(token);
        return ResponseEntity.ok(usersDTO);
    } catch (Exception e) {
        return ResponseEntity.status(401).build();
    }
}

@DeleteMapping("/delete/{userId}")
public ResponseEntity<String> managerDeleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Integer userId) {
    String token = authorizationHeader.substring(7);
    try {
        int rowsAffected = userService.managerDeleteUser(token, userId);
        if(rowsAffected == 1) {
            return ResponseEntity.status(200).body("Account deleted successfully.");
        }
        else{
            return ResponseEntity.status(200).body("No account found with the given ID.");
        }
    } catch (Exception e) {
        return ResponseEntity.status(401).build();
    }
}

}
