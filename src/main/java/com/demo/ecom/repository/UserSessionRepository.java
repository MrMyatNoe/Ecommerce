package com.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.ecom.entity.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Long>{

	@Query(value = "SELECT * FROM UserSession u WHERE u.email = ?1", nativeQuery = true)
	UserSession getUserSessionByEmail(String email);
}
