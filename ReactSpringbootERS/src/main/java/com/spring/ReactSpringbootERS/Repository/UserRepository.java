package com.spring.ReactSpringbootERS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ReactSpringbootERS.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User findByUserId(Integer userId);
}