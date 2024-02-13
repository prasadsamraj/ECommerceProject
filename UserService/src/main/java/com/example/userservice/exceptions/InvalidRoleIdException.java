package com.example.userservice.exceptions;

public class InvalidRoleIdException extends Exception{
    public InvalidRoleIdException() {
        super("Role id incorrect error");
    }
}
