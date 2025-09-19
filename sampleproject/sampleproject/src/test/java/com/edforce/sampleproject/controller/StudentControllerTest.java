package com.edforce.sampleproject.controller;

import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.edforce.sampleproject.service.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Mock
	private MockMvc mvc;
	
	@Mock
	private StudentService service;
}
