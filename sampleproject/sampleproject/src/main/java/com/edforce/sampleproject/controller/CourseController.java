package com.edforce.sampleproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edforce.sampleproject.dto.CourseDto;
import com.edforce.sampleproject.entity.Course;
import com.edforce.sampleproject.service.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	private final CourseService service;
	
	@PostMapping
	public ResponseEntity<Course> createCourse(Course course) {
		return ResponseEntity.ok(service.createCourse(course));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseDto> retrieveCourseDetails(@PathVariable Long id) {
		return ResponseEntity.ok(service.getCourseDetailsById(id));
	}
}
