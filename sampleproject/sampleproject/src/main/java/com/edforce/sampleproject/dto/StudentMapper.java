package com.edforce.sampleproject.dto;

import com.edforce.sampleproject.entity.Course;
import com.edforce.sampleproject.entity.Student;

public class StudentMapper {

	public static Student mapStudentDtoToStudent(StudentDto dto) {
	        if (dto == null) {
	            return null;
	        }
	        Student student = new Student();
	        student.setFirstName(dto.getFirstName());
	        student.setLastName(dto.getLastName());
	        student.setEmail(dto.getEmail());

	        if (dto.getCourse() != null) {
	            student.setCourseId(dto.getCourse().getCourseId());
	        }

	        return student;
	}
	
	public static StudentDto mapStudentToStudentDto(Student student, Course course) {
		if (student == null) {
            return null;
        }
        return new StudentDto(
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            course
        );
	}
	
}
