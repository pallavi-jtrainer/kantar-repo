package com.edforce.sampleproject.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edforce.sampleproject.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	Optional<Course> findByCourseName(String name);
	Optional<Course> findByCourseId(Long id);
	List<Course> findAllByStartDate(LocalDate start);
	
	@Modifying
	@Query(value = "update courses set duration = :duration where course_id = :id", nativeQuery=true)
	int updateDuration(@Param("id") Long id, @Param("duration") String duration);
}
