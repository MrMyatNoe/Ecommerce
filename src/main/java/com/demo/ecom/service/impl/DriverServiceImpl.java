package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Driver;
import com.demo.ecom.repository.DriverRepository;
import com.demo.ecom.service.IDriverService;

@Service
public class DriverServiceImpl implements IDriverService {

	@Autowired
	DriverRepository driverRepo;
	
	@Override
	public List<Driver> getAllDatas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Driver saveData(Driver d) {
		return driverRepo.save(d);
	}

	@Override
	public Driver updateData(Driver d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Driver getDataById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
