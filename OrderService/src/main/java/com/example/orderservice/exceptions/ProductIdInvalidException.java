package com.example.orderservice.exceptions;

public class ProductIdInvalidException extends Exception{
    public ProductIdInvalidException() {
        super("The product id is invalid!");
    }
}
