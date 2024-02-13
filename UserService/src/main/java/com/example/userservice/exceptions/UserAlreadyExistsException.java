package com.example.userservice.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("User creation failed. Reason: Email already signed up");
    }
}
