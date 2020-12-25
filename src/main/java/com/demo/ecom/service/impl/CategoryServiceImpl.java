package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Category;
import com.demo.ecom.repository.CategoryRepository;
import com.demo.ecom.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	CategoryRepository catRepo;
	
	@Override
	public List<Category> getAllDatas() {
		return catRepo.findAll();
	}

	@Override
	public Category saveData(Category t) {
		t.setCreated_date(System.currentTimeMillis());
		t.setUpdated_date(t.getCreated_date());
		return catRepo.save(t);
	}

	@Override
	public Category updateData(Category t) {
		Category searchCategory = getDataById(t.getId());
		t.setCreated_date(searchCategory.getCreated_date());
		t.setUpdated_date(System.currentTimeMillis());
		return catRepo.save(t);
	}

	@Override
	public void deleteById(long id) {
		catRepo.deleteById(id);
	}

	@Override
	public Category getDataById(long id) {
		try {
			return catRepo.findById(id).get();
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Category findByName(String name) {
		return catRepo.findByName(name);
	}

}
