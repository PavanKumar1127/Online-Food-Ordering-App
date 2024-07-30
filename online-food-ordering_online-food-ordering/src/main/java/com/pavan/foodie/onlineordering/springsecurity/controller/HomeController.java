package com.pavan.foodie.onlineordering.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public ResponseEntity<String> home() {
		return new ResponseEntity<>("Welcome to Online Food Ordering App", HttpStatus.OK);
	}

	@GetMapping("/signin")
	public String signin() {
		return "forward:/signin.html";
	}

}
