package com.tavant.spring.di.SpringDiDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tavant.spring.di.SpringDiDemo.entity.Book;

@Configuration
@ComponentScan(value = "com.tavant.spring.di.SpringDiDemo")
public class AppConfig {

	@Bean
	public Book createBook() {
		return new Book(1, "Learn Spring", 230.00);
	}
}
