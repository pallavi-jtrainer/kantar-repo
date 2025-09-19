package com.edforce.minibankapplication.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @ToString
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@Column(length=50, nullable = false)
	private String firstName;
	
	@Column(length=50, nullable = false)
	private String lastName;
	
	@Column(length=50, nullable = false, unique = true)
	private String email;
	
	@Column(length=50, nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Account> accounts;
	
}
