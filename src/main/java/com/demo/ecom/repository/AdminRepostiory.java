package com.demo.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Admin;

@Repository
public interface AdminRepostiory extends JpaRepository<Admin, Long> {

	Optional<Admin> findByName(String name);
	
	@Query(value = "SELECT * FROM Admin a WHERE a.email = ?1", nativeQuery = true)
	Optional<Admin> findByEmail(String email);
}
