package com.tavant.spring.core.SpringXMLConfigurationDemo.entity;

public class HelloWorld {
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void init() throws Exception {
		System.out.println("In the init method of the bean");
	}
	
	public void destroy() throws Exception {
		System.out.println("Bean destroyed.");
	}
}
