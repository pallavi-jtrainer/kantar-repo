package com.edforce.minibankapplication.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edforce.minibankapplication.entity.Customer;
import com.edforce.minibankapplication.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository repo;
	
	
}
