package com.woowa.board.noti.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
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
    
    @Async("threadPoolTaskExecutor")
	public void sendMail(RequestMail requestMail) throws MessagingException {

		log.info("sendMail ::: requestMail => " + requestMail);
		
		// Google 계정이 아닐 경우 보낸사람 필수
		if ( fromMail == null || "".equals(fromMail)) {
			log.error("이메일 발신자 정보가 없습니다.");
			return;			
		}
		if ( requestMail.getAddress() == null || "".equals(requestMail.getAddress())){
			log.error("이메일 수신자 정보가 없습니다.");
			return;
		}
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(requestMail.getAddress());
        message.setSubject(requestMail.getTitle());
        message.setText(requestMail.getMessage());
        emailSender.send(message);
        
        log.info("success sendMail");
		
	}
}
