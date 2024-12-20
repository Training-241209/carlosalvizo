package com.spring.ReactERS.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class Users {

    @Column(name = "userId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;


    public Users(){

    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName; 
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId.equals(users.userId);
    }

}
