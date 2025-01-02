package com.spring.ReactSpringbootERS.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDTO {


    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public UsersDTO(String firstName, String lastName, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public UsersDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UsersDTO(Integer userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UsersDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}