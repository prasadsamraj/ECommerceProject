package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.ExceptionDto;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(RazorpayException.class)
    public ResponseEntity<ExceptionDto> razorPayExceptionHandler(RazorpayException razorpayException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST, razorpayException.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StripeException.class)
    public ResponseEntity<ExceptionDto> stripeExceptionHandler(StripeException stripeException){
        return new ResponseEntity<>(new ExceptionDto(HttpStatus.BAD_REQUEST, stripeException.getMessage()), HttpStatus.BAD_REQUEST);

    }
}
