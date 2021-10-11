package com.demo.ecom.service;

import java.util.List;

import com.demo.ecom.entity.Leave;

public interface ILeaveService extends IAsyncService<Leave>{

    List<Leave> getDatasByPageAndSize(int page, int size);
}
