package com.edforce.sampleproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edforce.sampleproject.dto.CourseDto;
import com.edforce.sampleproject.dto.CourseMapper;
import com.edforce.sampleproject.entity.Course;
import com.edforce.sampleproject.entity.Student;
import com.edforce.sampleproject.exception.ResourceNotFoundException;
import com.edforce.sampleproject.repository.CourseRepository;
import com.edforce.sampleproject.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository repo;
	private final StudentRepository studentRepo;
	
	public Course createCourse(Course c) {
		return repo.save(c);
	}
	
	public CourseDto getCourseDetailsById(Long id) {
//		return repo.findByCourseId(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Course Does not exist"));
		
		Course c = repo.findByCourseId(id).get();
		CourseDto dto = new CourseDto();
		
		if (c != null) {
			List<Student> students = studentRepo.findAllByCourseId(id).get();
//			dto.setCourseName(c.getCourseName());
//			dto.setDescription(c.getDescription());
//			dto.setDuration(c.getDuration());
//			dto.setStartDate(c.getStartDate());
//			dto.setStudents(students);
			dto = CourseMapper.mapCourseToCourseDto(c, students);
		} else 
			throw new ResourceNotFoundException("Course does not exist");
		
		return dto;
	}
	
	public Course getCourseDetailsByName(String name) {
		return repo.findByCourseName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Course Does not exist"));
	}
}
