package com.example.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(ProductIdInvalidException.class)
    public ResponseEntity<ExceptionDto> productIdInvalidExceptionHandler(ProductIdInvalidException productIdInvalidException){
        return new ResponseEntity<>(new ExceptionDto(productIdInvalidException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidUUIDException.class)
    public ResponseEntity<ExceptionDto> invalidUUIDExceptionHandler(InvalidUUIDException invalidUUIDException){
        return new ResponseEntity<>(new ExceptionDto(invalidUUIDException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidOrderIdException.class)
    public ResponseEntity<ExceptionDto> invalidOrderIdExceptionHandler(InvalidOrderIdException invalidOrderIdException){
        return new ResponseEntity<>(new ExceptionDto(invalidOrderIdException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<ExceptionDto> invalidUserIdExceptionHandler(InvalidUserIdException invalidUserIdException){
        return new ResponseEntity<>(new ExceptionDto(invalidUserIdException.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
}
