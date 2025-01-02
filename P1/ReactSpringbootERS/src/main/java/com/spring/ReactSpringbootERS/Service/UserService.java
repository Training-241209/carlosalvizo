package com.spring.ReactSpringbootERS.Service;

import com.spring.ReactSpringbootERS.DTOs.UsersDTO;

import com.spring.ReactSpringbootERS.Entity.Roles;
import com.spring.ReactSpringbootERS.Entity.User;
import com.spring.ReactSpringbootERS.Exception.IncorrectEmailPasswordException;
import com.spring.ReactSpringbootERS.Exception.PasswordException;
import com.spring.ReactSpringbootERS.Exception.UniqueEmailException;
import com.spring.ReactSpringbootERS.Repository.UserRepository;
import com.spring.ReactSpringbootERS.Utility.PasswordUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    RolesService roleService;

    @Autowired
    JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UsersDTO> managerGetUsers(String token) {

        User user = jwtService.decodeToken(token);
        User userEmail = userRepository.findByEmail(user.getEmail());

        if("manager".equals(getUserRole(userEmail))){
            List<User> users = userRepository.findAll();
            List<UsersDTO> usersDTO = new ArrayList<>();
            for (User user2 : users) {
                if("employee".equals(user2.getRole().getRoleName())){
                    usersDTO.add(new UsersDTO(user2.getUserId(),user2.getFirstName(), user2.getLastName(), user2.getEmail()));
                }
            }
            return usersDTO;
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not a manager");
        }
    }
    

    public User addUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UniqueEmailException("Email already exists");
        }

        if (user.getPassword().length() < 5) {
            throw new PasswordException("Password must be at least 5 characters");
        }

        Roles employeeRole = roleService.getOrCreateRole("employee");

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        newUser.setRole(employeeRole);
        userRepository.save(newUser);
        return newUser;
    }

    public User login(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            User temp = userRepository.findByEmail(user.getEmail());

            if (PasswordUtil.checkPassword(user.getPassword(), temp.getPassword())) {
                return temp;
            }
            throw new IncorrectEmailPasswordException("Incorrect email and/or password.");

        }
        throw new IncorrectEmailPasswordException("Incorrect email and/or password.");
    }

    public int managerDeleteUser(String token, Integer userId) {

        User user = jwtService.decodeToken(token);
        User userEmail = userRepository.findByEmail(user.getEmail());
        
        if("manager".equals(getUserRole(userEmail))){
            Optional<User> userToDelete = userRepository.findById(userId);
            if(userToDelete.isPresent()){
                userRepository.delete(userToDelete.get());
                return 1;
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not a manager");
        }
    }

    public String getUserRole(User user) {
        if (user.getRole() != null) {
            return user.getRole().getRoleName();
        }
        throw new IllegalStateException("User does not have a role assigned.");
    }
}