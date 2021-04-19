package com.demo.ecom.service;

import com.demo.ecom.entity.Category;

public interface ICategoryService extends IBaseService<Category>{
	
	Category findByName(String name);

}
