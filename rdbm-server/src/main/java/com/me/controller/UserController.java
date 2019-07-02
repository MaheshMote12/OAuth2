package com.me.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.model.UserProfile;

@RestController
@RequestMapping("/api")
public class UserController {

	@GetMapping("/profile")
	public ResponseEntity<UserProfile> userProfile() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User principal = (User)authentication.getPrincipal();
		
		return ResponseEntity.ok(new UserProfile(principal.getUsername(), principal.getUsername()+"@mailto.com"));
	}
	
	@ExceptionHandler(Exception.class)
	public String defaultExceptionHandler() {
		return "User is not protected with Spring's HTTP authentication";
	}
}
