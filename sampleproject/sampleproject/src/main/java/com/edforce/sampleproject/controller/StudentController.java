package com.edforce.sampleproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.edforce.sampleproject.dto.StudentDto;
import com.edforce.sampleproject.entity.Student;
import com.edforce.sampleproject.exception.ResourceNotFoundException;
import com.edforce.sampleproject.service.CourseService;
import com.edforce.sampleproject.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Student REST API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService service;
	//private final CourseService courseService;
	
//	public StudentController(StudentService service) {
//		this.service = service;
//	}
	
		
//	@PostMapping
//	public ResponseEntity<Student> createStudent(@RequestBody StudentDto s) {
//		return ResponseEntity.ok(service.createStudent(s));
//	}
	
	@Operation(
			summary = "Create a new Student",
			description = "Adds a new student to the database",
			tags= {"Create New Student"})
	@ApiResponses({
		@ApiResponse(description = "201 Created", 
				content = {@Content(schema = @Schema(implementation = Student.class))},
				responseCode = "201"),
		@ApiResponse(description = "200 Ok", 
			content = {@Content(schema = @Schema(implementation = Student.class))},
			responseCode = "200")
		
	})
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student s) {
		return ResponseEntity.ok(service.createStudent(s));
	}
	
	@Operation(
			summary = "Retrieve a list of Students",
			description = "returns a list of students",
			tags= {"Get a list of students"})
	@ApiResponses({
		@ApiResponse(description = "OK 200", 
				content = {@Content(schema = @Schema(implementation = Student.class))},
				responseCode = "200")
		
	})
	@GetMapping
	public ResponseEntity<List<Student>> listAllStudents() {
		return ResponseEntity.ok(service.listAllStudents());
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Optional<Student>> getStudentDetails(@PathVariable Long id) {
//		return ResponseEntity.ok(service.getStudentData(id));
//	}
	
	@Operation(
		      summary = "Retrieve a Student by Id",
		      description = "Get a StudentDto object by specifying the id. The response is Student object with firstname, lastname, email and course details",
		      tags = { "Get Student By Id" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = StudentDto.class), mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StudentDto>> getStudentDetails(@PathVariable Long id) {
		return ResponseEntity.ok(service.getStudentData(id));
	}
	
	@PatchMapping("/update")
	public ResponseEntity<String> updateCourseId(@RequestParam Long id, @RequestParam Long course) {
		return ResponseEntity.ok(service.updateCourse(id, course));
	}
}
