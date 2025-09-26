package com.edforce.todobackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "todos")
@Getter @Setter @ToString
@NoArgsConstructor
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="userid", nullable = false)
	private Long userId;
	
	@Column(length = 150, nullable=false, unique=true)
	private String title;
	
	@Column(nullable = false)
	private boolean completed;

	public Todo(Long userId, String title, boolean completed) {
		this.userId = userId;
		this.title = title;
		this.completed = completed;
	}
	
	
}
