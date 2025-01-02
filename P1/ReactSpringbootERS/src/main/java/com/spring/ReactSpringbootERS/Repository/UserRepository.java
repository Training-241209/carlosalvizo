package com.spring.ReactSpringbootERS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.ReactSpringbootERS.DTOs.UsersDTO;
import com.spring.ReactSpringbootERS.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User findByUserId(Integer userId);

    @Query("SELECT new com.spring.ReactSpringbootERS.DTOs.UsersDTO(u.firstName, u.lastName, u.email, u.role.roleName) FROM User u WHERE u.role.roleName = :role")
    List<UsersDTO> findAllEmployees(@Param("role") String role);

}