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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reimburstment> reimburstments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="roleId")
    private Roles role;

}
