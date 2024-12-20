package com.spring.ReactERS.entity;

import javax.persistence.*;

@Entity
@Table(name = "reimbursements")

public class Reimburstments {

    @Column(name="reimbId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reimbId;

    @Column(name = "description")
    private String description;

    private Integer amount;

    @Column(name = "status")
    private String status;
    
    @Column(name = "userId")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private Users user;

    public Reimburstments() {

    }

    public Reimburstments(Integer reimbId, String description, Integer amount, String status, Integer userId) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public Integer getReimbId() {
        return reimbId;
    }

    public void setReimbId(Integer reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reimburstments{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimburstments that = (Reimburstments) o;
        return reimbId.equals(that.reimbId);
    }


}
