package com.example.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public PaymentService(PaymentGatewayChooserStrategy paymentGatewayChooserStrategy) {
        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy;
    }

    public String generatePaymentLink(String orderId) throws RazorpayException, StripeException {
        return paymentGatewayChooserStrategy.getBestPaymentGateway().generatePaymentLink(orderId);
    }
}
