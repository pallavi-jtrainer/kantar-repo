package com.edforce.todobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edforce.todobackend.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("update User u set u.password = :pass where u.email = :email")
	int updatePassword(@Param("email") String email, @Param("pass") String pass);
}
