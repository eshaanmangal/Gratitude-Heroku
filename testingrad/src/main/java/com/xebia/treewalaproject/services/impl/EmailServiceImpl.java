package com.xebia.treewalaproject.services.impl;

import com.xebia.treewalaproject.services.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailServiceImpl {

    @Autowired
    private EmailSender emailSender;

    private String _SUBJECT = "Thank You Card Received!";
    private String _TEXT = "How are you feeling?";

    public void sendEmail(Set<String> emailIds) throws Exception {
        Session session = emailSender.authenticate();

        CompletableFuture.runAsync(() -> {
            emailIds.stream().forEach(recipient -> {
                emailSender.prepareAndSendMail(session, recipient, _SUBJECT, _TEXT);
            });
        }).exceptionally(exc -> {
            exc.printStackTrace();
            return null;
        });
    }
}
