package com.demo.ecom.repository;

import com.demo.ecom.entity.DailyTransaction;
import com.demo.ecom.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long>{

}
