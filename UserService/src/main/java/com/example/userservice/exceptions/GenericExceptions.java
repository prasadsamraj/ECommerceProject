package com.example.userservice.exceptions;

import com.example.userservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptions {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST, userAlreadyExistsException.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidUserCredentialsException.class)
    public ResponseEntity<ExceptionDto> invalidUserCredentialsException(InvalidUserCredentialsException invalidUserCredentialsException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST, invalidUserCredentialsException.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<ExceptionDto> invalidUserIdException(InvalidUserIdException invalidUserIdException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, invalidUserIdException.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidRoleIdException.class)
    public ResponseEntity<ExceptionDto> invalidRoleIdException(InvalidRoleIdException invalidRoleIdException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, invalidRoleIdException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
