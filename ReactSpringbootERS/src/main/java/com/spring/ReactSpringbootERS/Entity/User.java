package com.spring.ReactSpringbootERS.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<Reimburstment> reimburstments = new ArrayList<>();

}
