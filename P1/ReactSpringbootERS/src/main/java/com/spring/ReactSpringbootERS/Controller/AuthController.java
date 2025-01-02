package com.spring.ReactSpringbootERS.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ReactSpringbootERS.Entity.User;
import com.spring.ReactSpringbootERS.Exception.UniqueEmailException;
import com.spring.ReactSpringbootERS.Repository.UserRepository;
import com.spring.ReactSpringbootERS.Service.JwtService;
import com.spring.ReactSpringbootERS.Service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("register")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User newUser = userService.addUser(user);
            return ResponseEntity.ok().body(newUser);
        }catch(UniqueEmailException ex){
            return ResponseEntity.status(409).build();
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        User loggedUser = userService.login(user);
        if(loggedUser != null){
            String token = jwtService.generateToken(loggedUser);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("firstName", userRepository.findByEmail(loggedUser.getEmail()).getFirstName());
            response.put("lastName", userRepository.findByEmail(loggedUser.getEmail()).getLastName());
            response.put("role" , userRepository.findByEmail(loggedUser.getEmail()).getRole().getRoleName());
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(409).build();
    }

    @GetMapping("me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                User userToken = jwtService.decodeToken(token);
                if (userToken != null && !jwtService.isTokenInvalid(token)) {
                    User user = userRepository.findByEmail(userToken.getEmail());
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.status(401).body("Invalid or expired token.");
                }
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Server error: " + e.getMessage());
            }
        }
        return ResponseEntity.status(400).body("Invalid token format.");
    }
    

    @PostMapping("logout")
    public ResponseEntity<String> logoutUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                jwtService.invalidateToken(token);
                return ResponseEntity.ok("Logout successful.");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Failed to invalidate token.");
            }
        }
        return ResponseEntity.status(400).body("Authorization header missing or invalid.");
    }
    
    
}
