package com.work.community.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
   
   private final JavaMailSender javaMailSender;

   public void sendTemporaryPassword (String email, String temporaryPassword) {
   SimpleMailMessage message = new SimpleMailMessage();
   message.setSubject("임시 비밀번호 발급 안내");
   message. setTo (email);
   message.setText("임시 비밀번호 :"+ temporaryPassword);
   //이메일 전송
   javaMailSender.send(message);
   
   
   }

}