package com.spring.ReactSpringbootERS.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ReactSpringbootERS.DTOs.UsersDTO;
import com.spring.ReactSpringbootERS.Entity.User;
import com.spring.ReactSpringbootERS.Exception.UniqueEmailException;
import com.spring.ReactSpringbootERS.Service.JwtService;
import com.spring.ReactSpringbootERS.Service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;



    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsers(){
        List<UsersDTO> users = userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
    }

    
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
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(409).build();
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        int rowsAffected = userService.deleteUser(userId);
        if (rowsAffected == 1) {
            return ResponseEntity.status(200).body("Account deleted successfully.");
        } else {
            return ResponseEntity.status(200).body("No account found with the given ID.");
        }
    }


}
