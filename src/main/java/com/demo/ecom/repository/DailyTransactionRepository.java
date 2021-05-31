package com.demo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.DailyTransaction;

@Repository
public interface DailyTransactionRepository extends JpaRepository<DailyTransaction, Long>{

}
