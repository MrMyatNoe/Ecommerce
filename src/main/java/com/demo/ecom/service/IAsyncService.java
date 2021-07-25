package com.demo.ecom.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Async;

@Transactional
public interface IAsyncService<T> {
	
	@Async("asyncExecutor")
	CompletableFuture<List<T>> getAllDatas();
	
	@Async("asyncExecutor")
	CompletableFuture<T> saveData(T t);
	
	@Async("asyncExecutor")
	CompletableFuture<T> updateData(T t);
	
	@Async("asyncExecutor")
	void deleteById(long id);
	
	@Async("asyncExecutor")
	CompletableFuture<T> getDataById(long id);
}
