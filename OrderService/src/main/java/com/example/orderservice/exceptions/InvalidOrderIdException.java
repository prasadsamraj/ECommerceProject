package com.example.orderservice.exceptions;

public class InvalidOrderIdException extends Exception{
    public InvalidOrderIdException() {
        super("Order Id is invalid!");
    }
}
