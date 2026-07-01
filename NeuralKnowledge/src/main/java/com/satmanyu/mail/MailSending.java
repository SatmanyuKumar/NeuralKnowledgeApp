package com.satmanyu.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.satmanyu.jwt.JwtUtil;

import jakarta.mail.internet.MimeMessage;


@Service

public class MailSending {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private JwtUtil jwtUtil;
	@Value("${app.base-url}")
	private String baseUrl;
	
	public String mailSendResetPassword(String remail) {
		try {
			
			String sub="Password Reset Request";
			String token = jwtUtil.generateToken(remail);
			String resetLink = baseUrl + "/resetPassword?token=" + token;
			String body="Click the link to reset your password: "+resetLink;
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setTo(remail);
			mailMessage.setSubject(sub);
			mailMessage.setText(body);
			
			mailSender.send(mailMessage);
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}
	public String mailSendResetPasswordHTML(String remail) {
		try {
			String sub="Password Reset Request";
			String token = jwtUtil.generateToken(remail);
			String resetLink = baseUrl + "/resetPassword?token=" + token;
			String body="<h1 style='background-color:blue;color:white;padding:20px;'>Congrats!</h1> "
			+ "<p style='background-color:yellow;padding:20px;'>Click the link to reset your password: "+resetLink+"!</p>";
			MimeMessage mailMessage=mailSender.createMimeMessage();
			boolean multiPart=true;
			MimeMessageHelper helper=new MimeMessageHelper(mailMessage,multiPart);
			helper.setTo(remail);
			helper.setSubject(sub);
			helper.setText("text/html",body);
			mailSender.send(mailMessage);
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}
}
