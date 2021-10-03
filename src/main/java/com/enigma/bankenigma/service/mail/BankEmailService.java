package com.enigma.bankenigma.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BankEmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String mailTo,String subject, String messageBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(messageBody);
        javaMailSender.send(message);
    }
}
