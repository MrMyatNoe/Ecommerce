package com.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
