package com.edforce.sampleproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.edforce.sampleproject.dto.StudentDto;
import com.edforce.sampleproject.entity.Course;
import com.edforce.sampleproject.entity.Student;
import com.edforce.sampleproject.exception.ResourceNotFoundException;
import com.edforce.sampleproject.repository.CourseRepository;
import com.edforce.sampleproject.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

	@Mock
	private StudentRepository repo;
	
	@Mock
	private CourseRepository courseRepo;
	
	@InjectMocks
	private StudentService service;
	
	@Test
	public void testCreateStudent() {
		//arrange		
		Student student = new Student();
		student.setStudentId(1L);
		student.setFirstName("first");
		student.setLastName("last");
		student.setEmail("first@kj.c");
		student.setCourseId(10L);
		
		//act
//		Student s = repo.save(student);
		when(repo.save(any(Student.class))).thenReturn(student);
		
		Student s = service.createStudent(student);
		
		//assert
		assertThat(student.getFirstName()).isEqualTo(s.getFirstName());	
		
	}
	
	@Test
	public void testGetStudentDetails() {
		Course course = new Course();
		course.setCourseId(10L);
		course.setCourseName("Testing");
		course.setDescription("Testing course");
		course.setDuration("3 days");
		course.setStartDate(LocalDate.now());
		
		Student student = new Student();
		student.setStudentId(1L);
		student.setFirstName("first");
		student.setLastName("last");
		student.setEmail("first@kj.c");
		student.setCourseId(10L);
		
//		Optional<StudentDto> dto = new StudentDto();
//		dto.setFirstName(student.getFirstName());
//		dto.setLastName("last");
//		dto.setEmail("sdlkfj@aksfn");
//		dto.setCourse(course);
		
		when(repo.findByStudentId(1L)).thenReturn(Optional.of(student));
		
//		assertThrows(ResourceNotFoundException.class, 
//				() -> service.getStudentDetails(2L));
		
		assertNotNull(student);
		
		when(courseRepo.findByCourseId(10L)).thenReturn(Optional.of(course));
		
		Optional<StudentDto> dto = service.getStudentData(1L);	
		
		assertThat(dto.isPresent());
		
	}
	
	@Test
	public void testListAllStudents() {
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1L, "abc", "def", "sldkfj@snsdf", 10L));
		students.add(new Student(2L, "abc", "def", "sldkj@snsdf", 100L));
		students.add(new Student(3L, "abc", "def", "slkfj@snsdf", 20L));
		students.add(new Student(4L, "abc", "def", "sdkfj@snsdf", 15L));
		
		when(repo.findAll()).thenReturn(students);
		
		List<Student> s = service.listAllStudents();
		
		assertEquals(students,s);
	}
	
	@Test
	public void testGetStudentDetails_NotFound() {
		
		when(repo.findByStudentId(2L)).thenReturn(Optional.empty());
		
		ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
				() -> service.getStudentData(2L));
		
		assertThat(ex.getMessage()).isEqualTo("Student Not Found");
	}
}
