package com.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
