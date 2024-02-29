package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.GeneratePaymentRequestDto;
import com.example.paymentservice.services.PaymentService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String generatePaymentLink(@RequestBody GeneratePaymentRequestDto requestDto) throws RazorpayException, StripeException {
        return paymentService.generatePaymentLink(requestDto.getOrderId());
    }
}
