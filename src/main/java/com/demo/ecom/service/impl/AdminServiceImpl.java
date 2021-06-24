package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Admin;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.AdminRespostiory;
import com.demo.ecom.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService{

	@Autowired
	AdminRespostiory adminRepo;
	
	@Override
	public List<Admin> getAllDatas() {
		return adminRepo.findAll();
	}

	@Override
	public Admin saveData(Admin a) {
		a.setCreated_date(System.currentTimeMillis());
		a.setUpdated_date(a.getCreated_date());
		return adminRepo.save(a);
	}

	@Override
	public Admin updateData(Admin a) {
		System.out.print("service " +a);
		Admin searchAdmin = getDataById(a.getId());
		a.setCreated_date(searchAdmin.getCreated_date());
		a.setUpdated_date(System.currentTimeMillis());
		return adminRepo.save(a);
	}

	@Override
	public void deleteById(long id) {
		adminRepo.deleteById(id);
	}

	@Override
	public Admin getDataById(long id) {
		return adminRepo.findById(id).orElseThrow(()->new NotFoundException("Admin Not Found! "+ id));
	}

	@Override
	public void delete() {
		this.adminRepo.deleteAll();
	}

	
}
