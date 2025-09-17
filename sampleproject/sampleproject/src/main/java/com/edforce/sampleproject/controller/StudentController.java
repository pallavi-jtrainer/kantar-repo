package com.edforce.sampleproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.edforce.sampleproject.entity.Student;
import com.edforce.sampleproject.exception.ResourceNotFoundException;
import com.edforce.sampleproject.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService service;
	
	public StudentController(StudentService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student s) {
		return ResponseEntity.ok(service.createStudent(s));
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> listAllStudents() {
		return ResponseEntity.ok(service.listAllStudents());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Student>> getStudentDetails(@PathVariable Long id) {
		return ResponseEntity.ok(service.getStudentData(id));
	}
	
	@PatchMapping("/update")
	public ResponseEntity<String> updateCourseId(@RequestParam Long id, @RequestParam Long course) {
		return ResponseEntity.ok(service.updateCourse(id, course));
	}
}
