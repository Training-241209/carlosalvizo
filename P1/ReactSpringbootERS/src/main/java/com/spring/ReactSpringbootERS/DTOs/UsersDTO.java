package com.spring.ReactSpringbootERS.DTOs;

import lombok.Data;

@Data
public class UsersDTO {

    private String firstName;
    private String lastName;
    private String email;

    public UsersDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}