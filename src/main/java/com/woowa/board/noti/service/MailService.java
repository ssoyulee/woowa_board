package com.woowa.board.noti.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.woowa.board.noti.vo.RequestMail;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;
    
	public void sendMail(RequestMail requestMail) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wjlee0612@gmail.com");
        message.setTo(requestMail.getAddress());
        message.setSubject(requestMail.getTitle());
        message.setText(requestMail.getMessage());
        emailSender.send(message);
		
	}
}