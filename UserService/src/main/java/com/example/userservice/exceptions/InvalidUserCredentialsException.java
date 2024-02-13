package com.example.userservice.exceptions;

public class InvalidUserCredentialsException extends Exception{
    public InvalidUserCredentialsException() {
        super("Email or Password invalid");
    }
}
