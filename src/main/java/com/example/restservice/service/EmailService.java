package com.example.restservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${internal.email}")
    private String INTERNAL_EMAIL;

    public void sendEmail(String to, JsonNode emailContent) {
        SimpleMailMessage message = new SimpleMailMessage();

        //email content should be in the format: {"emailBody": "...", "subject": "..."}
        String subject = emailContent.has("subject") ? emailContent.get("subject").asText() : "No Subject";
        String body = emailContent.has("emailBody") ? emailContent.get("emailBody").asText() : "No Content";

        message.setFrom(INTERNAL_EMAIL);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        System.out.println("Sending email to: " + to);
        System.out.println("Email subject: " + subject);
        System.out.println("Email body: " + body);
        mailSender.send(message);
    }

}
