package com.pmtech.entifribackend.service;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class SendEmailService {


    @Autowired
    private EmailService emailService;


    @Value("${spring.mail.username}")
    private String username;

    public void sendingEmail(String subject, String messageContent, String to) throws AddressException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress(username))
                //.replyTo(new InternetAddress(to))
                .to(Lists.newArrayList(new InternetAddress(to)))
                .subject(subject)
                .body(messageContent)
                .encoding("UTF-8").build();

        emailService.send(email);
    }
}
