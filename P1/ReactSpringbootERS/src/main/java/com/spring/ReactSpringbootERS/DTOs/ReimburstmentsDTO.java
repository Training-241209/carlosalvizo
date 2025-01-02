package com.spring.ReactSpringbootERS.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReimburstmentsDTO {
    
    private Integer reimbId;
    private String description;
    private Double amount;
    private String status;
    private UsersDTO user;

    public ReimburstmentsDTO(Integer reimbId, String description, Double amount, String status) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    public ReimburstmentsDTO(Integer reimbId, String description, Double amount, String status, UsersDTO user) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }


}
