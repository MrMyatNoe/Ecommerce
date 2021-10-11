package com.demo.ecom.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.AlreadyExistsException;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.RoleRepository;
import com.demo.ecom.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleRepository roleRepo;

	@Override
	public CompletableFuture<List<Role>> getAllDatas() {
		return CompletableFuture.completedFuture(roleRepo.findAll());
	}

	@Override
	public CompletableFuture<Role> saveData(Role r) {
		if (existByName(r)) 
			throw new AlreadyExistsException("Role already exists");
		return CompletableFuture.completedFuture(this.roleRepo.save(r));
	}

	@Override
	public CompletableFuture<Role> updateData(Role r) {
		Role searchRole = getDataById(r.getId()).join();
		r.setCreated_date(searchRole.getCreated_date());
		r.setUpdated_date(System.currentTimeMillis());
		roleRepo.save(r);
		return CompletableFuture.completedFuture(r);
	}

	@Override
	public void deleteById(long id) {
		getDataById(id);
		roleRepo.deleteById(id);
	}

	@Override
	public CompletableFuture<Role> getDataById(long id) {
		Role searchRole = roleRepo.findById(id).orElseThrow(() -> new NotFoundException("Role Not Found!" + id));
		return CompletableFuture.completedFuture(searchRole);
	}

	private boolean existByName(Role r) {
		Role existRole = roleRepo.findByName(r.getName());
		return existRole !=null &&  existRole.getId() > 0 && existRole.getId() != r.getId() ;
	}
	
	@Override
	public Role findByName(String name) throws DemoBasedException {
		Role role = roleRepo.findByName(name);
		if (role == null) {
			throw new NotFoundException("Role Not Found!" + name);
		}
		return roleRepo.findByName(name);
	}
}
