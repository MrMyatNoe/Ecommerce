package com.demo.ecom.service;

import java.util.List;

import com.demo.ecom.entity.DailyTransaction;

public interface IDailyTransactionService extends IAsyncService<DailyTransaction>{

    List<DailyTransaction> getDatasByPageAndSize(int page, int size);
    
}
