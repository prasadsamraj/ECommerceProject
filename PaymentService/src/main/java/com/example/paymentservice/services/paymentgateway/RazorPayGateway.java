package com.example.paymentservice.services.paymentgateway;

import com.razorpay.PaymentLink;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public class RazorPayGateway implements PaymentGateway{

    @Override
    public String generatePaymentLink(String orderId) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("key", "secret");
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
        paymentLinkRequest.put("expire_by", 1711607709);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for policy no #23456");
        JSONObject customer = new JSONObject();
        customer.put("name","prasad");
        customer.put("contact","+91123456789");
        customer.put("email","prasad@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",false);
        notify.put("email",false);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",false);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.google.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = razorpay.paymentLink.create(paymentLinkRequest);
        return paymentLink.toString();
    }
}
