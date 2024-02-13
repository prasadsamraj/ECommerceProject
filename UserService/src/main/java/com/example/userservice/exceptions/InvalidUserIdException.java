package com.example.userservice.exceptions;

public class InvalidUserIdException extends Exception{
    public InvalidUserIdException() {
        super("Invalid User Id error");
    }
}
