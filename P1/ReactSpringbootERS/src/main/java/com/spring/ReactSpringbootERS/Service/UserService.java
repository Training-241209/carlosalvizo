package com.spring.ReactSpringbootERS.Service;

import com.spring.ReactSpringbootERS.Entity.User;
import com.spring.ReactSpringbootERS.Exception.IncorrectEmailPasswordException;
import com.spring.ReactSpringbootERS.Exception.PasswordException;
import com.spring.ReactSpringbootERS.Exception.UniqueEmailException;
import com.spring.ReactSpringbootERS.Repository.UserRepository;
import com.spring.ReactSpringbootERS.Utility.PasswordUtil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user){

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UniqueEmailException("Email already exists");
        }

        if(user.getPassword().length() < 5){
            throw new PasswordException("Password must be at least 5 characters");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        newUser.setRole("employee");
        userRepository.save(newUser);
        return newUser;
    }
    

    public User login(User user) {

        if(userRepository.existsByEmail(user.getEmail())){
            User temp = userRepository.findByEmail(user.getEmail());
            
            
            if (PasswordUtil.checkPassword(user.getPassword(), temp.getPassword())) {
                return temp;
            }
            throw new IncorrectEmailPasswordException("Incorrect email and/or password.");
            
        }
        throw new IncorrectEmailPasswordException("Incorrect email and/or password.");
    }

    public int deleteUser(Integer userId){
        Optional<User> delUser = userRepository.findById(userId);
        if(delUser.isPresent()){
            userRepository.delete(delUser.get());
            return 1;
        }
        return 0;
    }

}