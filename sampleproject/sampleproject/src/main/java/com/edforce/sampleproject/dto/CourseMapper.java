package com.edforce.sampleproject.dto;

import java.util.List;

import com.edforce.sampleproject.entity.Course;
import com.edforce.sampleproject.entity.Student;

public class CourseMapper {

	public static CourseDto mapCourseToCourseDto(Course c, List<Student> students) {
		return new CourseDto(
				c.getCourseName(), c.getDuration(), c.getStartDate(),
				c.getDescription(), students
				);
	}
}
