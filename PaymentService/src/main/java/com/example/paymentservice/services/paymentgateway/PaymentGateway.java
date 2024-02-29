package com.example.paymentservice.services.paymentgateway;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface PaymentGateway {
    String generatePaymentLink(String orderId) throws RazorpayException, StripeException;
}
