package com.edforce.sampleproject.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edforce.sampleproject.dto.StudentDto;
import com.edforce.sampleproject.dto.StudentMapper;
import com.edforce.sampleproject.entity.Course;
import com.edforce.sampleproject.entity.Student;
import com.edforce.sampleproject.exception.ResourceNotFoundException;
import com.edforce.sampleproject.exception.WrongInputException;
import com.edforce.sampleproject.repository.CourseRepository;
import com.edforce.sampleproject.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private static Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	private final StudentRepository repo;
	private final CourseRepository courseRepo;
	
//	private final StudentMapper mapper;
	
//	public StudentService(StudentRepository repo) {
//		this.repo = repo;
//	}
//	
	public Student createStudent(Student s) {
		return repo.save(s);
	}
	
//	public Student createStudent(StudentDto dto) {
//		Student s = StudentMapper.mapStudentDtoToStudent(dto);
//		return repo.save(s);
//	}
	
	/**
	 * method to return a list of students
	 * @return a list of students
	 */
	public List<Student> listAllStudents() {
		return repo.findAll();
	}
	
	/**
	 * transactional method to update a row partially
	 * @param id Long, holds the id of the student
	 * @param course
	 * @return
	 */
	@Transactional
	public String updateCourse(Long id, Long course) {
		String str = "Unable to update course";
		
//		if(course == null) {
//			throw new WrongInputException("Wrong input");
//		}
		
		Optional<Student> s = repo.findByStudentId(id);
		
		if(s.isEmpty()) {
			throw new ResourceNotFoundException("Student not found");
		}
		
		int res = repo.updateCourseId(id, course);
		
		if(res > 0) {
			str = "Course id updated";
		}
		
		return str;
	}
	
	
	public Optional<StudentDto> getStudentData(Long id) {
		Student s = repo.findByStudentId(id).get();
		StudentDto dto = new StudentDto();
		
		if(s != null) {
			Course c = courseRepo.findByCourseId(s.getCourseId()).get();
			
			if(c == null)
				throw new ResourceNotFoundException("Course not found");
			
			dto = StudentMapper.mapStudentToStudentDto(s, c);
		} else {
			throw new ResourceNotFoundException("Student Not Found");
		}
		
		return Optional.of(dto);
	}
	
//	public Optional<Student> getStudentData(Long id) {
////		Optional<Student> s = Optional.of(repo.findByStudentId(id).get());
//////				.orElseThrow(() -> new RuntimeException("Student Not Found")
//////				);
////		
////		return s;
//		
//		return Optional.of(repo.findByStudentId(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Student not found")));
//		
//	}
}
