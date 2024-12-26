package com.spring.ReactSpringbootERS.Controller;

import java.util.List;

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
import com.spring.ReactSpringbootERS.Service.ReimburstmentService;

@RestController
@RequestMapping("/reimburstments")
public class ReimburstmentsController {

    @Autowired
    private ReimburstmentService reimburstmentService;

    @GetMapping
    public ResponseEntity<List<ReimburstmentsDTO>> getAllReimburstments(){
        List<ReimburstmentsDTO> reimburstments = reimburstmentService.getAllReimburstments();
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
    

