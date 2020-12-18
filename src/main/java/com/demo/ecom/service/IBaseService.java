package com.demo.ecom.service;

import java.util.List;

import javax.transaction.Transactional;

@Transactional
public interface IBaseService<T> {

	List<T> getAllDatas();
	
	T saveData(T t);
	
	T updateData(T t);
	
	void deleteById(long id);
	
	T getDataById(long id);
}
