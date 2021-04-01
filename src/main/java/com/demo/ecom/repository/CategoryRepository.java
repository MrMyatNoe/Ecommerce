package com.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query(value = "SELECT * from Category where name = ?1",nativeQuery = true)
	Category findByName(String name);
}
