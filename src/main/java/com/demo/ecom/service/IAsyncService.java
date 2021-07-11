package com.demo.ecom.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface IAsyncService<T> {
	
	CompletableFuture<List<T>> getAllDatas();
	
	CompletableFuture<T> saveData(T t);
	
	CompletableFuture<T> updateData(T t);
	
	void deleteById(long id);
	
	CompletableFuture<T> getDataById(long id);
}
