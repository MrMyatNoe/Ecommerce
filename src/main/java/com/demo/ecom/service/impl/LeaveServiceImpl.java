package com.demo.ecom.service.impl;

import com.demo.ecom.entity.Car;
import com.demo.ecom.entity.Driver;
import com.demo.ecom.entity.Leave;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.LeaveRepository;
import com.demo.ecom.service.ICarService;
import com.demo.ecom.service.IDriverService;
import com.demo.ecom.service.ILeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LeaveServiceImpl implements ILeaveService {

    @Autowired
    LeaveRepository repository;

    @Autowired
    ICarService carService;
    
    @Autowired
    IDriverService driverService;

    @Override
    public CompletableFuture<List<Leave>> getAllDatas() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Override
    public CompletableFuture<Leave> saveData(Leave leave) {
        Car car = carService.getDataById(leave.getCarId());
        leave.setCar(car);
        
        Driver driver = driverService.getDataById(leave.getDriverId());
        leave.setDriver(driver);
        
        leave.setCreated_date(System.currentTimeMillis());
        leave.setUpdated_date(leave.getCreated_date());
        
        repository.save(leave);
        return CompletableFuture.completedFuture(leave);
    }

    @Override
    public CompletableFuture<Leave> updateData(Leave leave) {
        Leave existedLeave = getDataById(leave.getId()).join();
        Car car = carService.getDataById(leave.getCarId());
        leave.setCar(car);
        leave.setCreated_date(existedLeave.getCreated_date());
        leave.setUpdated_date(System.currentTimeMillis());
        repository.save(leave);
        return CompletableFuture.completedFuture(leave);
    }

    @Override
    public void deleteById(long id) {
        getDataById(id);
        repository.deleteById(id);
    }

    @Override
    public CompletableFuture<Leave> getDataById(long id) {
        Leave existedLeave = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("leave not exist" + id));
        return CompletableFuture.completedFuture(existedLeave);
    }

//    @Override
//    public List<Leave> getDatasByPageAndSize(int page, int size) {
//        System.out.println(page + " : "+ size);
//        Page<Leave> pages = repository.findAll(PageRequest.of(page, size));
//
//        List<Leave> list = pages.getContent();
//        list.stream().forEach(leave -> {leave.setCarNo(leave.getCar().getCarNo());
//        leave.setCarId(leave.getCar().getId());
//        leave.setDriverName(leave.getDriver().getName());
//        leave.setDriverId(leave.getDriver().getId());});
//        return list;
//    }
}
