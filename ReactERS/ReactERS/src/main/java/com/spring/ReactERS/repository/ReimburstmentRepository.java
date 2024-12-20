package com.spring.ReactERS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ReactERS.entity.Reimburstments;

@Repository
public interface ReimburstmentRepository extends JpaRepository<Reimburstments, Integer> {
    
    List<Reimburstments> findByUserId(Integer userId);

}
