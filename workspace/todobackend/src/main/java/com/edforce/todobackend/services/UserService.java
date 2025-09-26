package com.edforce.todobackend.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edforce.todobackend.dto.UserDto;
import com.edforce.todobackend.entity.User;
import com.edforce.todobackend.mapper.CustomMapper;
import com.edforce.todobackend.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repo;
	private final CustomMapper mapper;
	
	public UserService(UserRepository repo, CustomMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public User registerUser(UserDto u) {
		User user = mapper.userDtoToUser(u);
		return repo.save(user);
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
