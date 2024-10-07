package com.pipespace.pipespace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pipespace.pipespace.controllers.HomeController;

@Service
@EnableScheduling
public class PingService {

	@Autowired
	private HomeController homeController;
	
	@Scheduled(fixedRate = 30000)
	public void pingOnServer() {
		 String response = homeController.welcome();
		 
		 System.out.println("Ping on server : " + response);
	}
}
