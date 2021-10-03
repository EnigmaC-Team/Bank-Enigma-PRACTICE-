package com.enigma.bankenigma.service.mail_services;

import com.enigma.bankenigma.string_properties.MailServiceString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class BankEmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String mailTo, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject(MailServiceString.OTP_SUBJECT);
        message.setText(String.format(
                MailServiceString.OTP_MESSAGE_BODY,
                token
        ));
        javaMailSender.send(message);
    }
}
