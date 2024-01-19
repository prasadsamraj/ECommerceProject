package com.example.productservice.exceptions;

public class InvalidCategoryException extends Exception{
    public InvalidCategoryException(String message){
        super(message);
    }
}
