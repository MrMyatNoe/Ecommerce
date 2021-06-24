package com.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.Admin;

@Repository
public interface AdminRespostiory extends JpaRepository<Admin, Long>{

}
