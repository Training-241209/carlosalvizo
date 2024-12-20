package com.spring.ReactSpringbootERS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.ReactSpringbootERS.Entity.Reimburstment;
import com.spring.ReactSpringbootERS.Entity.User;
import com.spring.ReactSpringbootERS.Exception.UniqueEmailException;
import com.spring.ReactSpringbootERS.Service.JwtService;
import com.spring.ReactSpringbootERS.Service.ReimburstmentService;
import com.spring.ReactSpringbootERS.Service.UserService;

@RestController
public class ReimburstmentController {

    @Autowired
    private ReimburstmentService reimburstmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

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
    public ResponseEntity<String> loginUser(@RequestBody User user){
        User loggedUser = userService.login(user);
        if(loggedUser != null){
            String token = jwtService.generateToken(loggedUser);
            return ResponseEntity.status(200).body(token);
        }
        return ResponseEntity.status(409).build();
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(200).body(users);
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

    
    @GetMapping("reimburstments")
    public ResponseEntity<List<Reimburstment>> getAllReimburstments(){
        List<Reimburstment> reimburstments = reimburstmentService.getAllReimburstments();
        return ResponseEntity.status(200).body(reimburstments);
    }

 @PostMapping("/add-reimburstment")
    public ResponseEntity<Reimburstment> addReimburstment(
            @RequestHeader("Authorization") String authorizationHeader, 
            @RequestBody Reimburstment reimburstment) {
                String token = authorizationHeader.substring(7);
        try {
            Reimburstment newReimburstment = reimburstmentService.addReimburstments(token, reimburstment);
            return ResponseEntity.ok(newReimburstment);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(403).body(null);
        }
    }

    @PatchMapping("/update-reimburstment/{reimId}")
    public ResponseEntity<Reimburstment> updateReimburstment(
        @RequestHeader("Authorization") String authorizationHeader,
        @PathVariable Integer reimId) 
        {
            String token = authorizationHeader.substring(7);
            try {
                Reimburstment updatedReimburstment = reimburstmentService.updateReimburstment(token, reimId);
                return ResponseEntity.ok(updatedReimburstment);
            } catch (ResponseStatusException ex) {
                return ResponseEntity.status(403).body(null);
            }
    }
}


