package com.woowa.board.noti.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.woowa.board.noti.vo.RequestMail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;
    
    @Value("${spring.mail.username}")
    private String fromMail;
    
	public void sendMail(RequestMail requestMail) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(requestMail.getAddress());
        message.setSubject(requestMail.getTitle());
        message.setText(requestMail.getMessage());
        emailSender.send(message);
        
        log.info("success sendMail");
		
	}
}
