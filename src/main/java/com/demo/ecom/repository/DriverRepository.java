package com.demo.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	@Query(value = "SELECT * FROM Driver d WHERE d.phone = ?1", nativeQuery = true)
	Optional<Driver> findByPhone(String phone);
	
	Optional<Driver> findByName(String name);
}
