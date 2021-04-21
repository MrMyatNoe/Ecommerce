package com.demo.ecom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	@Query(value = "SELECT * from Tutorial where title = ?1", nativeQuery = true)
	Tutorial findByTitle(String title);
	
	Page<Tutorial> findByTitleContaining(String title,Pageable pageable);

	@Query(value = "SELECT COUNT(*) FROM Tutorial WHERE published = true",nativeQuery = true)
	Long getPublishedCounts();
	
	@Query(value = "SELECT COUNT(*) FROM Tutorial WHERE published = false",nativeQuery = true)
	Long getPendingCounts();
}
