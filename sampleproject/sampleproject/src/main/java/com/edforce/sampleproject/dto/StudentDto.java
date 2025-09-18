package com.edforce.sampleproject.dto;

import com.edforce.sampleproject.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

	private String firstName;
	private String lastName;
	private String email;
	private Course course;
}
