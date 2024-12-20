package com.spring.ReactERS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ReactERS.repository.ReimburstmentRepository;

import com.spring.ReactERS.entity.*;

import java.util.List;

@Component
public class ReimburstmentService {
    
    ReimburstmentRepository reimburstmentRepository;

    @Autowired
    public ReimburstmentService(ReimburstmentRepository reimburstmentRepository){
        this.reimburstmentRepository = reimburstmentRepository;
    }

    public List<Reimburstments> getAllReimburstments(){
        return reimburstmentRepository.findAll();
    }

    public List<Reimburstments> getReimburstmentsByUserId(Users user){
        return reimburstmentRepository.findByUserId(user.getUserId());
    }

    public Reimburstments addReimburstments(Users user, Reimburstments reimburstment){
        Reimburstments newReimburstments = new Reimburstments();
        newReimburstments.setDescription(reimburstment.getDescription());
        newReimburstments.setAmount(reimburstment.getAmount());
        newReimburstments.setStatus("Pending");
        newReimburstments.setUserId(user.getUserId());
        reimburstmentRepository.save(newReimburstments);
        return newReimburstments;
    }



}
