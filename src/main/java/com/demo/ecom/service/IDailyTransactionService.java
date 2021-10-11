package com.demo.ecom.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.ecom.entity.DailyTransaction;

public interface IDailyTransactionService extends IBaseService<DailyTransaction>{

    List<DailyTransaction> getDatasByPageAndSize(int page, int size);
    
    Page<DailyTransaction> getDailysByPageAndSize(int page, int size);
    
}
