package com.edforce.todobackend.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edforce.todobackend.entity.User;
import com.edforce.todobackend.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repo;
	
	public UserService(UserRepository repo) {
		this.repo = repo;
	}
	
	public User registerUser(User u) {
		return repo.save(u);
	}
	
	public Optional<User> retrieveUserDetailsByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Optional<User> retrieveUserDetailsByUserName(String username) {
		return repo.findByUsername(username);
	}
	
	public Optional<User> retrieveUserDetailsById(Long id) {
		return repo.findById(id);
	}
	
	public String updatePassword(String email, String pass) {
		User u = repo.findByEmail(email).get();
		
		if(u != null) {
			int res = repo.updatePassword(email, pass);
			if(res > 0) {
				return "Password updated for user with email: " + email;
			} else {
				return "Unable to update password user with email: " + email;
			}
		} else {
			throw new RuntimeException("User with email: " + email + " not found");
		}
	}
}
