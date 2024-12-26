package com.spring.ReactSpringbootERS.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reimburstments")
public class Reimburstment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reimbId;

    private String description;
    private Double amount;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "user_Id")
    @JsonBackReference
    private User user;

}
