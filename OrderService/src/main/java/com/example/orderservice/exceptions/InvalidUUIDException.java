package com.example.orderservice.exceptions;

public class InvalidUUIDException extends Exception{
    public InvalidUUIDException() {
        super("UUID invalid error!");
    }
}
