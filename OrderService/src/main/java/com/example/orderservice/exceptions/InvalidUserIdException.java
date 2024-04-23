package com.example.orderservice.exceptions;

public class InvalidUserIdException extends Exception{
    public InvalidUserIdException() {
        super("User Id is invalid!");
    }
}
