package com.spring.ReactSpringbootERS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ReactSpringbootERS.Entity.Reimburstment;

public interface ReimburstmentRepository extends JpaRepository<Reimburstment, Integer> {
    
    List<Reimburstment> findByUserId(Integer userId);

    Optional<Reimburstment> findById(Integer reimbId);

}
