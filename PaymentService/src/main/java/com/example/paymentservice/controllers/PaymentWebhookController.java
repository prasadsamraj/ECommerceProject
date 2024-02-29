package com.example.paymentservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("webhooks")
public class PaymentWebhookController {
    @PostMapping
    public void paymentWebhook(){
        System.out.println("\nWebhook Received Successfully.....\n");
    }
}
