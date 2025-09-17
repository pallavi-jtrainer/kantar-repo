package com.edforce.sampleproject.entity;

import java.time.LocalDate;

//import org.springframework.data.annotation.CreatedDate;
//
//import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courses")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	
	@Column(length=100, nullable = false, unique = true)
	private String courseName;
	
	@Column
	private String description;
	
	@Column(nullable = false, length=50)
	private String duration;
	
//	@CreatedDate
	@Column(nullable = false)
	private LocalDate startDate;
	
}
