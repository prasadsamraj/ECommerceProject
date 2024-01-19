package com.example.productservice.exceptions;

import com.example.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptions {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> notFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<ExceptionDto> invalidCategoryException(InvalidCategoryException invalidCategoryException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST, invalidCategoryException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
