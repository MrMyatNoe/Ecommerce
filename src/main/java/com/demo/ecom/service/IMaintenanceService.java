package com.demo.ecom.service;

import com.demo.ecom.entity.Maintenance;

import java.util.List;

public interface IMaintenanceService extends IAsyncService<Maintenance>{

    List<Maintenance> getDatasByPageAndSize(int page, int size);
    
}
