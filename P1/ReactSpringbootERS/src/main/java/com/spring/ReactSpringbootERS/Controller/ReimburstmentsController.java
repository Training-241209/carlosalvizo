package com.spring.ReactSpringbootERS.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.ReactSpringbootERS.DTOs.ReimburstmentsDTO;
import com.spring.ReactSpringbootERS.Entity.Reimburstment;
import com.spring.ReactSpringbootERS.Service.JwtService;
import com.spring.ReactSpringbootERS.Service.ReimburstmentService;

@RestController
@RequestMapping("/reimburstments")
public class ReimburstmentsController {

    @Autowired
    private ReimburstmentService reimburstmentService;

    @Autowired
    private JwtService jwtService;


    @GetMapping
    public ResponseEntity<List<ReimburstmentsDTO>> getAllReimburstments(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        try {
            if(!jwtService.isTokenInvalid(token)){
                List<ReimburstmentsDTO> reimburstments = reimburstmentService.getAllReimburstments(token);
                return ResponseEntity.status(200).body(reimburstments);
            }
            return ResponseEntity.status(403).build();
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<List<ReimburstmentsDTO>> getReimburstmentsByUser(@RequestHeader("Authorization") String authorizationHeader){
        System.out.println("Authorization Header: " + authorizationHeader);
        String token = authorizationHeader.substring(7);
        try {
            if(!jwtService.isTokenInvalid(token)){
                List<ReimburstmentsDTO> reimburstments = reimburstmentService.getReimburstmentsByUser(token);
                return ResponseEntity.status(200).body(reimburstments);
            }
            return ResponseEntity.status(403).body(null);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(403).body(null);
        }
    }

    @PostMapping("/add-reimburstment")
    public ResponseEntity<Reimburstment> addReimburstment(
            @RequestHeader("Authorization") String authorizationHeader, 
            @RequestBody Reimburstment reimburstment) {
            String token = authorizationHeader.substring(7);
        try {
            if(!jwtService.isTokenInvalid(token)){
                Reimburstment newReimburstment = reimburstmentService.addReimburstments(token, reimburstment);
                return ResponseEntity.ok(newReimburstment);
            }
            return ResponseEntity.status(403).body(null);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(403).body(null);
        }
    }

    @PatchMapping("/update-reimburstment/{reimId}")
    public ResponseEntity<Reimburstment> updateReimburstment(
        @RequestHeader("Authorization") String authorizationHeader,
        @PathVariable Integer reimId, @RequestBody Map<String, String> status) 
        {
            String token = authorizationHeader.substring(7);
            try {
                String newStatus = status.get("status");
                Reimburstment updatedReimburstment = reimburstmentService.updateReimburstment(token, reimId, newStatus);
                return ResponseEntity.ok(updatedReimburstment);
            } catch (ResponseStatusException ex) {
                return ResponseEntity.status(403).body(null);
            }
        }
}