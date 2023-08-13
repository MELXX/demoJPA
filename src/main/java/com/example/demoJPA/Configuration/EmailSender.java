package com.example.demoJPA.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    public Boolean SendEmail(String subject,String body,String addr){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        //change this
        message.setFrom("melusimgwenya@gmail.com");
        message.setTo(addr);
        message.setText(body);
        javaMailSender.send(message);
        return true;
    }
}
