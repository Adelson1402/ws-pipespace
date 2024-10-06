package com.pipespace.pipespace.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender jmSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	public String sendMail(String destinatario, String assunto, MultipartFile file) {
		
		try {
			MimeMessage mm = jmSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mm, true);
			helper.setFrom(from);
			helper.setTo(destinatario);
			helper.setSubject(assunto);
			helper.setText("Segue em anexo o Arquivo PDF.");
			helper.addAttachment(file.getOriginalFilename(), file);
			
			jmSender.send(mm);
			
			return "Email Enviado";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao enviar email";
		}
		
	}
}
