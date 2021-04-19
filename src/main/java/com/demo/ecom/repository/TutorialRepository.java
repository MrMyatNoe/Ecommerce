package com.demo.ecom.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Tutorial;

@SuppressWarnings("unused")
@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	@Query(value = "SELECT * from Tutorial where title = ?1", nativeQuery = true)
	Tutorial findByTitle(String title);
}
