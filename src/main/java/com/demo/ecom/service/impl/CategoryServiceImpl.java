package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Category;
import com.demo.ecom.exception.AlreadyExistsException;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.CategoryRepository;
import com.demo.ecom.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository catRepo;

	/**
	 * For testing purpose
	 * 
	 * @param repo
	 */
	public CategoryServiceImpl(CategoryRepository repo) {
		this.catRepo = repo;
	}

	@Override
	public List<Category> getAllDatas() {
		return catRepo.findAll();
	}

	@Override
	public Category saveData(Category c) {
		if (existByName(c.getName())) {
			throw new AlreadyExistsException("Category already exists");
		}
		c.setCreated_date(System.currentTimeMillis());
		c.setUpdated_date(c.getCreated_date());
		return catRepo.save(c);
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
//		try {
//			return catRepo.findById(id).get();
//		} catch (RuntimeException e) {
//			throw new RuntimeException(e.getMessage());
//		}
		return catRepo.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found!" + id));

	}

	@Override
	public Category findByName(String name) {
		return catRepo.findByName(name);
	}
	
	public boolean existByName(String name) {
		return findByName(name) != null;
	}

}
