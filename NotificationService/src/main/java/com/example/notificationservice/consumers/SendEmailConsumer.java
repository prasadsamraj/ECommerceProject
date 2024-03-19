package com.example.notificationservice.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SendEmailConsumer {
    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void handleSendEmail(String message){
        System.out.println("\nReceived Message: " + message+"\n");
    }
}
