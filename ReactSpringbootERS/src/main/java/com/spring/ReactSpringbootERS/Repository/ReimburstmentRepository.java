package com.spring.ReactSpringbootERS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ReactSpringbootERS.Entity.Reimburstment;
import com.spring.ReactSpringbootERS.Entity.User;

public interface ReimburstmentRepository extends JpaRepository<Reimburstment, Integer> {
    
    List<Reimburstment> findByUser(User user);

    Optional<Reimburstment> findById(Integer reimbId);

}
