package com.example.userservice.controllers;

import com.example.userservice.dtos.GenericUserDto;
import com.example.userservice.dtos.SetUserRolesRequestDto;
import com.example.userservice.exceptions.InvalidRoleIdException;
import com.example.userservice.exceptions.InvalidUserCredentialsException;
import com.example.userservice.exceptions.InvalidUserIdException;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("{id}")
    public ResponseEntity<GenericUserDto> getUserDetails(@PathVariable("id") String userId) throws InvalidUserIdException {
        GenericUserDto genericUserDto = userService.getUserDetails(userId);
        return new ResponseEntity<>(genericUserDto, HttpStatus.OK);
    }
    @PostMapping("{id}")
    public ResponseEntity<GenericUserDto> setUserRoles(@PathVariable("id") String userId, @RequestBody SetUserRolesRequestDto requestDto) throws InvalidRoleIdException, InvalidUserCredentialsException {
        GenericUserDto genericUserDto = userService.setUserRoles(userId, requestDto.getRoleIds());
        return new ResponseEntity<>(genericUserDto, HttpStatus.OK);
    }
}
