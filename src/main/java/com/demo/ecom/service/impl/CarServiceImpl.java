package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Car;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.CarRepository;
import com.demo.ecom.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	CarRepository carRepo;

	@Override
	public List<Car> getAllDatas() {
		return carRepo.findAll();
	}

	@Override
	public Car saveData(Car t) {
		t.setCreated_date(System.currentTimeMillis());
		t.setUpdated_date(t.getCreated_date());
		return this.carRepo.save(t);
	}

	@Override
	public Car updateData(Car t) {
		Car c = getDataById(t.getId());
		c.setCreated_date(System.currentTimeMillis());
		c.setUpdated_date(t.getCreated_date());
		return c;
	}

	@Override
	public void deleteById(long id) {
		this.carRepo.deleteById(id);
	}

	@Override
	public Car getDataById(long id) {
//		Car car = carRepo.findById(id).get();
//		if (car == null) 
//			throw new NotFoundException("Car is not found "  + id);
		return carRepo.findById(id).orElseThrow(() -> new NotFoundException("Car is not found " + id));
	}

}
