package com.example.userservice.services;

import com.example.userservice.models.Role;
import com.example.userservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String roleName) {
        Optional<Role> optionalRole = roleRepository.findByRole(roleName);
        if(optionalRole.isPresent()) return optionalRole.get();
        Role role = new Role();
        role.setRole(roleName);
        return roleRepository.save(role);
    }
}
