package com.example.userservice.controllers;

import com.example.userservice.dtos.CreateRoleRequestDto;
import com.example.userservice.models.Role;
import com.example.userservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequestDto requestDto){
        Role role = roleService.createRole(requestDto.getRole());
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }
}
