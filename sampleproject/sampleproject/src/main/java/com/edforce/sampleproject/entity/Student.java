package com.edforce.sampleproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long studentId; // student_id
	
	@Column(name="firstname", length=50, nullable = false)
	private String firstName;
	
	@Column(name="lastname", length=50, nullable=false)
	private String lastName;
	
	@Email
	@Column(name="email", length=150, nullable = false, unique = true)
	private String email;
	
//	@ManyToOne(targetEntity = Course.class, fetch = FetchType.LAZY)
	@Column(name="courseid", nullable=false)
	private long courseId;
	
}
