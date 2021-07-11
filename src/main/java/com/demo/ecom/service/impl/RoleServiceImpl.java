package com.demo.ecom.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.AlreadyExistsException;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.RoleRepository;
import com.demo.ecom.service.IAsyncService;
import com.demo.ecom.service.IRoleAsyncService;
import com.demo.ecom.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	IRoleAsyncService asyncService;

	@Override
	public List<Role> getAllDatas() {
//		CompletableFuture<List<Role>> listSync = asyncService.getAllRoles();
//		List<Role> list = listSync.join();
		//return asyncService.getAllDatas().join();
		return roleRepo.findAll();
	}

	@Override
	public Role saveData(Role r) {
		//return asyncService.saveData(r).join();
		if (existByName(r.getName())) {
			throw new AlreadyExistsException("Category already exists");
		}
		r.setCreated_date(System.currentTimeMillis());
		r.setUpdated_date(r.getCreated_date());
		return this.roleRepo.save(r);
	}

	@Override
	public Role updateData(Role r) {
		Role searchRole = getDataById(r.getId());
		r.setCreated_date(searchRole.getCreated_date());
		r.setUpdated_date(System.currentTimeMillis());
		return this.roleRepo.save(r);
	}

	@Override
	public void deleteById(long id) {
		roleRepo.deleteById(id);
	}

	@Override
	public Role getDataById(long id) {
		return roleRepo.findById(id).orElseThrow(() -> new NotFoundException("Role Not Found!" + id));
	}

	public boolean existByName(String name) {
		return findByName(name) != null;
	}

	@Override
	public Role findByName(String name) {
		try {
			return roleRepo.findByName(name);
		} catch (DemoBasedException e) {
			throw new NotFoundException("Role Not Found!" + name);
		}
	}
}
