package com.edforce.todobackend.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edforce.todobackend.dto.UserDto;
import com.edforce.todobackend.entity.User;
import com.edforce.todobackend.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<User> registerUser(@RequestBody UserDto u) {
		User user = service.registerUser(u);
		return ResponseEntity.created(URI.create("/api/users/" + user.getUsername())).body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getUserDetailsById(@PathVariable Long id) {
		return ResponseEntity.ok(service.retrieveUserDetailsById(id));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Optional<User>> getUserDetailsByEmail(@PathVariable String email) {
		return ResponseEntity.ok(service.retrieveUserDetailsByEmail(email));
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<Optional<User>> getUserDetailsByUserName(@PathVariable String username) {
		return ResponseEntity.ok(service.retrieveUserDetailsByUserName(username));
	}
}
