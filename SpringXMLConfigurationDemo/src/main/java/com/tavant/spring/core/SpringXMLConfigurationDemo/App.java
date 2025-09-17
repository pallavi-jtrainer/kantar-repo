package com.tavant.spring.core.SpringXMLConfigurationDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tavant.spring.core.SpringXMLConfigurationDemo.config.AppConfig;
import com.tavant.spring.core.SpringXMLConfigurationDemo.entity.Course;
import com.tavant.spring.core.SpringXMLConfigurationDemo.entity.HelloWorld;
import com.tavant.spring.core.SpringXMLConfigurationDemo.entity.Student;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	Student student = ctx.getBean(Student.class, "s");
    	System.out.println(student.getStudentId() + " " + student.getStudentName()
    			+ " " + student.getCourse().getCourseName());
    	
//    	HelloWorld hi = ctx.getBean(HelloWorld.class, "obj");
//    	System.out.println(hi.getMessage());
    	
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        
//        HelloWorld obj = context.getBean("demo", HelloWorld.class);
//        System.out.println(obj.getMessage());

//        Student s = context.getBean("student", Student.class);
//        
//        System.out.println("Student Name: " + s.getStudentName() + " " 
//        		+ "Course Name: " + s.getCourse().getCourseName());
        
    }
}
