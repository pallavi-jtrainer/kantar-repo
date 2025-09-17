package com.tavant.spring.core.SpringXMLConfigurationDemo.entity;

public class Course {
	private int courseId;
	private String courseName;
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
}
