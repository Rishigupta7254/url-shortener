package com.rishi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.entity.User;
import com.rishi.repository.userRepository;
import com.rishi.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final userRepository userRepository ;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public AuthController(userRepository userRepository,
			                 PasswordEncoder passwordEncoder,
			                 JwtService jwtService) {
	this.userRepository=userRepository;
	this.passwordEncoder=passwordEncoder;
	this.jwtService = new JwtService();
	}
	@PostMapping("/register")
	public ResponseEntity<String>register(@RequestBody User user){
		
		if(userRepository.findByusername(user.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("Username already exist");
			
		}
		if(userRepository.findByEmail(user.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Email already exist");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
		return ResponseEntity.ok("user register successfully");
		
	}
	@PostMapping("/login")
	public ResponseEntity<String>login(@RequestBody User user){
		
		User existingUser=userRepository.findByusername(user.getUsername()).orElse(null);
		
		if(existingUser==null) {
			return ResponseEntity.badRequest().body("Inalid username or password");
			
		}
		
		if(!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
			return ResponseEntity.badRequest().body("Invalid username or password");
			
		}
		String token = jwtService.generateToken(
		        existingUser.getUsername()
		);

		return ResponseEntity.ok(token);

	}}