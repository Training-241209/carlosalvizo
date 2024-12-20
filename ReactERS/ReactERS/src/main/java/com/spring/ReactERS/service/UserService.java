package com.spring.ReactERS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ReactERS.repository.UserRepository;
import com.spring.ReactERS.entity.Users;
import com.spring.ReactERS.exception.IncorrectEmailPasswordException;
import com.spring.ReactERS.exception.UniqueEmailException;

import java.util.List;

@Component
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users addUser(Users user){

        if(userRepository.existsByEmail(user.getEmail())){
            throw new UniqueEmailException("Email is already registered.");
        }

        // Add password checking stuff based off regex FUTURE

        Users newUser = new Users();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setRole("employee");
        userRepository.save(newUser);
        return newUser;
    }

    public Users login(Users user){

        if(userRepository.existsByEmail(user.getEmail())){
            Users temp = userRepository.findByEmail(user.getEmail());
            if(temp.getPassword().equals(user.getPassword())){
                return temp;
            }
            throw new IncorrectEmailPasswordException("Incorrect email and/or password.");

        }
        throw new IncorrectEmailPasswordException("Incorrect email and/or password.");
    }
    
    public String deleteUser(Users user){
        Users delUser = userRepository.findByEmail(user.getEmail());
        if(delUser != null){
            userRepository.delete(delUser);
        return "deleted";
        }  
        return "not-delted";
    }

}
