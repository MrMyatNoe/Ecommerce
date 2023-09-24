package com.demo.ecom.repository;

import com.demo.ecom.entity.Leave;
import com.demo.ecom.response.LeaveResponse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>{

//    select  SUM(days) as days,car_id,driver_id from demo_ecom.leaves l 
//    where startedDate  
//    BETWEEN '2021-10-12' and '2021-10-31'
//    GROUP BY car_id, driver_id 
    
    @Query(value = "SELECT * from leaves l \n"
           + "    WHERE startedDate  \n"
           + "    BETWEEN ?1 and ?2 \n",nativeQuery = true)
    List<Leave> getLeavesByFirstDateAndLastDate(String firstDate, String lastDate);
}
