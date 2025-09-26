package com.edforce.todobackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edforce.todobackend.entity.Todo;

import jakarta.transaction.Transactional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
	
	Optional<Todo> findByTitle(String title);
	Optional<List<Todo>> findAllByUserId(long id);
	Optional<List<Todo>> findAllByUserIdAndCompleted(Long id, boolean done);
	
	@Transactional
	@Modifying
	@Query("update Todo t set t.completed = :done where t.id = :id")
	int updateTodo(@Param("id") long id, @Param("done") boolean done);
	
}
