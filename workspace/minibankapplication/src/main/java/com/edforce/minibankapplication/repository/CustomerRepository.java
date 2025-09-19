package com.edforce.minibankapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edforce.minibankapplication.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	Optional<Customer> findByEmail(String email);
	boolean existsByEmail(String email);
	Optional<Customer> findByCustomerId(Long id);
}
