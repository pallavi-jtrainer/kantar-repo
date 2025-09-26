package com.edforce.todobackend.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edforce.todobackend.entity.Todo;
import com.edforce.todobackend.services.TodoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private final TodoService service;

	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Todo>> listAllTodos() {
		return ResponseEntity.ok(service.listAllTodos());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Optional<List<Todo>>> listAllTodosByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(service.listAllByUserId(id));
	}
	
	@PostMapping
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
		Todo t = service.addNewTodo(todo);
		return ResponseEntity.created(URI.create("/api/todos/" + t.getId())).body(t);
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<Optional<List<Todo>>> listAllByUserIdAndCompleted(@PathVariable long id, @RequestParam boolean done) {
		return ResponseEntity.ok(service.listAllByUserAndStatus(id, done));
	}
}
