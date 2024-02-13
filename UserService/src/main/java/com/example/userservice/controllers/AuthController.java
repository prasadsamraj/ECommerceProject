package com.example.userservice.controllers;

import com.example.userservice.dtos.*;
import com.example.userservice.exceptions.InvalidUserCredentialsException;
import com.example.userservice.exceptions.UserAlreadyExistsException;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<GenericUserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        GenericUserDto responseBody = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<GenericUserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws InvalidUserCredentialsException {
        return authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenRequestDto requestDto){
        SessionStatus sessionStatus = authService.validate(requestDto.getUserId(), requestDto.getToken());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        authService.logout(requestDto.getUserId(), requestDto.getToken());
        return ResponseEntity.ok().build();
    }
}
