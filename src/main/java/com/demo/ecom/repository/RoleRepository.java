package com.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query(value = "SELECT * from Role where name = ?1",nativeQuery = true)
	Role findByName(String name);
}
