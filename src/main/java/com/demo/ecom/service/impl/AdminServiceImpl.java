package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Admin;
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
	public Admin saveData(Admin t) {
		return adminRepo.save(t);
	}

	@Override
	public Admin updateData(Admin t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(long id) {
		adminRepo.deleteById(id);
	}

	@Override
	public Admin getDataById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		this.adminRepo.deleteAll();
	}

	
}
