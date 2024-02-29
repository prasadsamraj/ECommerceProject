package com.example.paymentservice.services;

import com.example.paymentservice.services.paymentgateway.PaymentGateway;
import com.example.paymentservice.services.paymentgateway.RazorPayGateway;
import com.example.paymentservice.services.paymentgateway.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayChooserStrategy {
    private RazorPayGateway razorPayGateway;
    private StripePaymentGateway stripePaymentGateway;
    @Autowired
    public PaymentGatewayChooserStrategy(RazorPayGateway razorPayGateway, StripePaymentGateway stripePaymentGateway) {
        this.razorPayGateway = razorPayGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway(){
        return stripePaymentGateway;
    }
}
