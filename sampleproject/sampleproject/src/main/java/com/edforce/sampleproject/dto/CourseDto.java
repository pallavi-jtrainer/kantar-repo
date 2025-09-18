package com.edforce.sampleproject.dto;

import java.time.LocalDate;
import java.util.List;

import com.edforce.sampleproject.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {

	private String courseName;
	private String duration;
	private LocalDate startDate;
	private String description;
	
	private List<Student> students;
}
