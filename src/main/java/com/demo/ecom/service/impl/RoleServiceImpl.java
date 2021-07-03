package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Role;
import com.demo.ecom.exception.AlreadyExistsException;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.RoleRepository;
import com.demo.ecom.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Role> getAllDatas() {
		return this.roleRepo.findAll();
	}

	@Override
	public Role saveData(Role r) {
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
		return roleRepo.findByName(name);
	}
}
