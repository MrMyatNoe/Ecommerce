package com.demo.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecom.entity.DailyTransaction;

@Repository
public interface DailyTransactionRepository extends CrudRepository<DailyTransaction, Long>{

}
