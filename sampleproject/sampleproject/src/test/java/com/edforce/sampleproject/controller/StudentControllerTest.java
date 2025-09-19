package com.edforce.sampleproject.controller;

//import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.edforce.sampleproject.dto.StudentDto;
import com.edforce.sampleproject.entity.Course;
import com.edforce.sampleproject.entity.Student;
import com.edforce.sampleproject.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper; //json object conversion. throws a jsonprocessingexception
	
	@MockitoBean
	private StudentService service;
	
	@Test
	public void testCreateStudent() {
		//input entity
		Student request = new Student(1L, "first", "last", "fkjs@sff", 10L);
		
		//response entity
 		Student response = new Student(1L, "first", "last", "fkjs@sff", 10L);
 		
 		//mock call
 		when(service.createStudent(any(Student.class))).thenReturn(response);
 		
 		try {
			mvc.perform(post("/api/students")
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName").value("first"))
				.andExpect(jsonPath("$.lastName").value("last"));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetStudentDetails() {
		Course course = new Course();
		course.setCourseId(10L);
		course.setCourseName("Testing");
		course.setDescription("Testing course");
		course.setDuration("3 days");
		course.setStartDate(LocalDate.now());
		
		StudentDto dto = new StudentDto();
		dto.setFirstName("first");
		dto.setLastName("last");
		dto.setEmail("sdlkfj@aksfn");
		dto.setCourse(course);
		
		when(service.getStudentData(1L)).thenReturn(Optional.of(dto));
		
		try {
			mvc.perform(get("/api/students/1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(dto)))
			.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("first"))
				.andExpect(jsonPath("$.course.courseName").value("Testing"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
