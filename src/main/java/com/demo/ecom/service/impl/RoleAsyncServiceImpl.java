package com.demo.ecom.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.AlreadyExistsException;
import com.demo.ecom.repository.RoleRepository;
import com.demo.ecom.service.IRoleAsyncService;

@Service
public class RoleAsyncServiceImpl implements IRoleAsyncService{
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Role>> getAllDatas() {
		try {
			TimeUnit.SECONDS.sleep(1);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Role> list = roleRepo.findAll();
		return CompletableFuture.completedFuture(list);
	}

	@Override
	public CompletableFuture<Role> saveData(Role r) {
		try {
			TimeUnit.SECONDS.sleep(1);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		////
		r.setCreated_date(System.currentTimeMillis());
		r.setUpdated_date(r.getCreated_date());
		roleRepo.save(r);
		return CompletableFuture.completedFuture(r);
	}

	@Override
	public CompletableFuture<Role> updateData(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(long id) {
		
	}

	@Override
	public CompletableFuture<Role> getDataById(long id) {
		return null;
	}

//	@Async("asyncExecutor")
//	public CompletableFuture<List<Role>> getAllRoles(){
//		try {
//			Thread.sleep(1000);
//		}catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		List<Role> list = roleRepo.findAll();
//		return CompletableFuture.completedFuture(list);
//	}
	
}
