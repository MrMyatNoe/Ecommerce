package com.demo.ecom.service;

import org.springframework.data.jpa.repository.Query;

import com.demo.ecom.entity.Category;

public interface ICategoryService extends IBaseService<Category>{
	
	Category findByName(String name);

}
