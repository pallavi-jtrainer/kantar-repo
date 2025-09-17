package com.tavant.spring.core.SpringXMLConfigurationDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tavant.spring.core.SpringXMLConfigurationDemo.entity.Course;
import com.tavant.spring.core.SpringXMLConfigurationDemo.entity.HelloWorld;
import com.tavant.spring.core.SpringXMLConfigurationDemo.entity.Student;

@Configuration
public class AppConfig {
	
//	@Bean
//	public HelloWorld hello() {
//		HelloWorld obj = new HelloWorld();
//		obj.setMessage("Welcome to Spring Java Config");
//		return obj;
//	}

	@Bean
	public Student studentInfo() {
		Student s = new Student();
		s.setStudentId(1);
		s.setStudentName("Some name");
		s.setCourse(courseInfo());
		return s;
	}
	
	@Bean
	public Course courseInfo() {
		Course c = new Course();
		c.setCourseId(1);
		c.setCourseName("Something");
		return c;
	}
}
