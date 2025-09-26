package com.edforce.todobackend.mapper;

import org.springframework.stereotype.Component;

import com.edforce.todobackend.dto.TodoDto;
import com.edforce.todobackend.dto.UserDto;
import com.edforce.todobackend.entity.Todo;
import com.edforce.todobackend.entity.User;

@Component
public class CustomMapper {

	public UserDto userToUserDto(User user) {
		UserDto dto = new UserDto();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		
		return dto;
	}
	
	public TodoDto todoToTodoDto(Todo t) {
		TodoDto dto = new TodoDto();
		dto.setUserId(t.getUserId());
		dto.setTitle(t.getTitle());
		dto.setCompleted(t.isCompleted());
		
		return dto;
	}
	
	public User userDtoToUser(UserDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		return user;
	}
	
	public Todo todoDtoToTodo(TodoDto dto) {
		Todo t = new Todo();
		t.setTitle(dto.getTitle());
		t.setUserId(dto.getUserId());
		t.setCompleted(dto.isCompleted());
		return t;
	}
}
