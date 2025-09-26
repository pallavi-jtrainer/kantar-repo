package com.edforce.todobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable = false, unique=true)
	private String username;
	
	@Column(nullable = false, unique=true)
	private String email;
	
	@Column(nullable = false)
	private String password;

	public User(String name, String username, String email, String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
}
