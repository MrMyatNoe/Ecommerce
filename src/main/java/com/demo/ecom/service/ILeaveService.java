package com.demo.ecom.service;


import java.util.List;

import com.demo.ecom.entity.Leave;
import com.demo.ecom.response.LeaveResponse;

public interface ILeaveService extends IAsyncService<Leave>{

    // List<Leave> getDatasByPageAndSize(int page, int size);
    
    List<LeaveResponse> getLeavesByFirstDateAndLastDate(String firstDate, String lastDate);
}
