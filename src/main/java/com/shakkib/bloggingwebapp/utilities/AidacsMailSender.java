package com.shakkib.bloggingwebapp.utilities;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("aidacsMailSender")
public class AidacsMailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);//true indicates multipart message
        helper.setFrom("md2076379@outlook.in");
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body, true);//true indicates body is html
        javaMailSender.send(message);
    }

}
