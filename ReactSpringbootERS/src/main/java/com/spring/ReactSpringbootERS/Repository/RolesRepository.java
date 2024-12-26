package com.spring.ReactSpringbootERS.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.ReactSpringbootERS.Entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{

    Optional<Roles> findByRoleName(String roleName);
    
}
