package com.example.demoJPA.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    public Boolean SendEmail(String subject,String body,String addr){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom("melusimgwenya@gmail.com");
        message.setTo(addr);
        message.setText(body);
        javaMailSender.send(message);
        return true;
    }

    private JavaMailSender getMailSender(){
        var impl = new JavaMailSenderImpl();
        impl.setHost("smtp.gmail.com");
        impl.setPassword("yiofiebaigenuovz");
        impl.setUsername("melusimgwenya@gmail.com");
        impl.setPort(465);
        impl.setProtocol("SSL");
        return impl;
    }
}
