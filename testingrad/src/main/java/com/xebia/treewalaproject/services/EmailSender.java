package com.xebia.treewalaproject.services;

import com.xebia.treewalaproject.client.MailContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailSender {

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Autowired
    private Environment env;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;


    public void setEmail(String recipient, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(text);
        sendMailUsingTLS(email);
    }

    public void sendMailUsingTLS(SimpleMailMessage email) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", port);
        sendMail(properties, userName, password, email);
    }

    public void sendMail(Properties properties,
                         String username, String password,
                         SimpleMailMessage email) {
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
                        return new PasswordAuthentication(username,
                                password);
                    }
                });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo()[0]));
            msg.setSubject(email.getSubject());
            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setHeader("MIME-version", "1.0");
            msg.setContent(mailContentBuilder.build(email.getTo()[0]), "text/html");

            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //////new Methods to send email. from above this line, all methods can be removed
    public Session authenticate() {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
                        return new PasswordAuthentication(userName,
                                password);
                    }
                });

        return session;
    }

    public void prepareAndSendMail(Session session, String recipient, String subject,
                                   String text) {
        String username = env.getProperty("spring.mail.username");

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(text);

        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.getTo()[0]));
            msg.setSubject(email.getSubject());
            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setHeader("MIME-version", "1.0");
            msg.setContent(mailContentBuilder.build(email.getTo()[0]), "text/html");

            Transport.send(msg);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
