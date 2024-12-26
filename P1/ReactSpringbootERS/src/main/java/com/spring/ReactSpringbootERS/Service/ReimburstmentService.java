package com.spring.ReactSpringbootERS.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring.ReactSpringbootERS.Entity.Reimburstment;
import com.spring.ReactSpringbootERS.Entity.User;

import io.jsonwebtoken.JwtException;

import com.spring.ReactSpringbootERS.Repository.ReimburstmentRepository;
import com.spring.ReactSpringbootERS.Repository.UserRepository;

@Service
public class ReimburstmentService {
    
    ReimburstmentRepository reimburstmentRepository;
    UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    public ReimburstmentService(ReimburstmentRepository reimburstmentRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.reimburstmentRepository = reimburstmentRepository;
    }

    public List<Reimburstment> getAllReimburstments(){
        return reimburstmentRepository.findAll();
    }

    public List<Reimburstment> getReimburstmentsByUserId(User user){
        return reimburstmentRepository.findByUserId(user.getUserId());
    }

    public Reimburstment addReimburstments(String token, Reimburstment reimburstment){

        User userToken;

        try {
            userToken = jwtService.decodeToken(token);
        } catch (JwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }

        User user = userRepository.findByEmail(userToken.getEmail());

        if("employee".equals(user.getRole())){
            Reimburstment newReimburstments = new Reimburstment();
            newReimburstments.setDescription(reimburstment.getDescription());
            newReimburstments.setAmount(reimburstment.getAmount());
            newReimburstments.setStatus("Pending");
            newReimburstments.setUserId(user.getUserId());
            reimburstmentRepository.save(newReimburstments);
            return newReimburstments;
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not an employee");
        }
    }

    public Reimburstment updateReimburstment(String token, Integer reimbId){

        User adminToken;

        try {
            adminToken = jwtService.decodeToken(token);
            
        } catch (JwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }

        User user = userRepository.findByEmail(adminToken.getEmail());

        if("manager".equals(user.getRole())){
            Optional<Reimburstment> reimburstment = reimburstmentRepository.findById(reimbId);
            if(reimburstment.isPresent()){
                Reimburstment temp = reimburstment.get();
                temp.setStatus("Approved");
                reimburstmentRepository.save(temp);
                return temp;
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reimburstment not found");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not a manager");
        }
    }

}
