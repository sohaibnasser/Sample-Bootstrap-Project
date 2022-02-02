package com.cs.ecom.conf;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendChangePasswordEmailHtml(String toAddress) {

		try {
			StringBuilder content = new StringBuilder();
			content.append("Dear User,\n Your password has been changed successfully.");
			sendEmailHtml(toAddress, content.toString(), "Password Reset");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendStatusEmailHtml(String toAddress,String status) {

		try {
			StringBuilder content = new StringBuilder();
			if(status!=null) {
				content.append("Dear User,\n Your application status has been"+" "+status+".");
				content.append("<div> \n \n \n \n <img src=\"https://w4work.com/wp-content/uploads/2020/04/cropped-logo-1-150x153.png\"/><div/>");
				sendEmailHtml(toAddress, content.toString(), status);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void sendEmailHtml(String toAddress, String content, String subject) {

		try {
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(content, "text/html");
			helper.setTo(toAddress);
			helper.setSubject(subject);
			emailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
