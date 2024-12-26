package com.spring.ReactSpringbootERS.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReimburstmentsDTO {
    
    private Integer reimbId;
    private String description;
    private Double amount;
    private String status;
    private UsersDTO user;


}
