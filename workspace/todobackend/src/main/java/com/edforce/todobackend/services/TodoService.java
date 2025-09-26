package com.edforce.todobackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edforce.todobackend.entity.Todo;
import com.edforce.todobackend.repository.TodoRepository;

@Service
public class TodoService {

	private final TodoRepository repo;

	public TodoService(TodoRepository repo) {
		this.repo = repo;
	}
	
	public List<Todo> listAllTodos() {
		return repo.findAll();
	}
	
	public Optional<List<Todo>> listAllByUserId(Long id) {
		return repo.findAllByUserId(id);
	}
	
	public Optional<List<Todo>> listAllByUserAndStatus(Long id, boolean status) {
		return repo.findAllByUserIdAndCompleted(id, status);
	}
	
	public Todo addNewTodo(Todo t) {
		return repo.save(t);
	}
	
	public String updateTodo(long id, boolean done) {
		Todo t = repo.findById(id).get();
		
		if(t != null) {
			int res = repo.updateTodo(id, done);
			
			if(res > 0) {
				return "Todo status updated";
			} else {
				return "unable to update todo";
			}
		} else {
			throw new RuntimeException("Todo not found");
		}
	}
}
