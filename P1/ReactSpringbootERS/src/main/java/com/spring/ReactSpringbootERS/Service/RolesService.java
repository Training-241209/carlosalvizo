package com.spring.ReactSpringbootERS.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ReactSpringbootERS.Entity.Roles;
import com.spring.ReactSpringbootERS.Repository.RolesRepository;

@Service
public class RolesService {

    RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles createRole(String roleName) {
        Roles role = new Roles();
        role.setRoleName(roleName);
        return rolesRepository.save(role);
    }

    public Roles getOrCreateRole(String roleName) {
        return rolesRepository.findByRoleName(roleName).orElseGet(() -> createRole(roleName));
    }
    
}
