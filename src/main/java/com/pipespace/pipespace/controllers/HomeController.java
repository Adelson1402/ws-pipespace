package com.pipespace.pipespace.controllers;

import java.util.Map;

import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pipespace.pipespace.services.EmailService;
import com.pipespace.pipespace.services.RequestService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.QueryParam;

@RestController
@RequestMapping(value="/api")
public class HomeController {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "<h1>SISTEMA ONLINE</h1>";
	}
	
	@PostMapping("send-pdf")
	public void filePdfToSend(@RequestParam("file") MultipartFile file) {
		emailService.sendMail("adelsonsantospinheiro@gmail.com", "Requisição recebida", file);
	}
	
	@PostMapping("body-request")
	public Object bodyRequest(@RequestBody String obj) {
		
		try {
			
			requestService.save(obj);
			messagingTemplate.convertAndSend("/topic/messages", obj);
		//	emailService.sendMail("adelsonsantospinheiro@gmail.com", "Requisição recebida", obj);
			return obj.toString();
			
		} catch (InvalidContentTypeException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	
}
