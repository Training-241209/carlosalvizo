package com.spring.ReactSpringbootERS.Entity;

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
    private Integer amount;
    private String status;
    
    @Column(name = "user_id")
    private Integer userId;

}
