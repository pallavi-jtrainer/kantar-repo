package com.tavant.spring.di.SpringDiDemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tavant.spring.di.SpringDiDemo.config.AppConfig;
import com.tavant.spring.di.SpringDiDemo.service.BookService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        
        BookService service = ctx.getBean(BookService.class);
        service.showBookDetails();
    }
}
