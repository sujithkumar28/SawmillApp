package com.sawmill.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WelcomeController {
	
	
	@Value("${app.name}")
	private String appName;
	
	@GetMapping("/")
	public String welcome(){
		return "Welcome to "+appName;
	}

}
