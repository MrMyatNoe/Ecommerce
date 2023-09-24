package com.demo.ecom.service.impl;

import com.demo.ecom.entity.Car;
import com.demo.ecom.entity.Maintenance;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.MaintenanceRepository;
import com.demo.ecom.service.ICarService;
import com.demo.ecom.service.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MaintenanceServiceImpl implements IMaintenanceService {

    @Autowired
    MaintenanceRepository repository;

    @Autowired
    ICarService carService;

    @Override
    public CompletableFuture<List<Maintenance>> getAllDatas() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

    @Override
    public CompletableFuture<Maintenance> saveData(Maintenance maintenance) {
        Car car = carService.getDataById(maintenance.getCarId());
        maintenance.setCar(car);
        maintenance.setCreated_date(System.currentTimeMillis());
        maintenance.setUpdated_date(maintenance.getCreated_date());
        repository.save(maintenance);
        return CompletableFuture.completedFuture(maintenance);
    }

    @Override
    public CompletableFuture<Maintenance> updateData(Maintenance maintenance) {
        Maintenance maintain = getDataById(maintenance.getId()).join();
        Car car = carService.getDataById(maintenance.getCarId());
        maintenance.setCar(car);
        maintenance.setCreated_date(maintain.getCreated_date());
        maintenance.setUpdated_date(System.currentTimeMillis());
        repository.save(maintenance);
        return CompletableFuture.completedFuture(maintenance);
    }

    @Override
    public void deleteById(long id) {
        getDataById(id);
        repository.deleteById(id);
    }

    @Override
    public CompletableFuture<Maintenance> getDataById(long id) {
        Maintenance maintain = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Maintenance not exist" + id));
        return CompletableFuture.completedFuture(maintain);
    }

//    @Override
//    public List<Maintenance> getDatasByPageAndSize(int page, int size) {
//        System.out.println(page + " : "+ size);
//        Page<Maintenance> pages = repository.findAll(PageRequest.of(page, size));
//
//        List<Maintenance> list = pages.getContent();
//        list.stream().forEach(maintenance -> {maintenance.setCarNo(maintenance.getCar().getCarNo());
//            maintenance.setCarId(maintenance.getCar().getId());});
//        return list;
//    }
}
