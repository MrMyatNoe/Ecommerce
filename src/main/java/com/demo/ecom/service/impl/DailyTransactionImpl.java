package com.demo.ecom.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.DailyTransaction;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.DailyTransactionRepository;
import com.demo.ecom.service.IDailyTransactionService;

@Service
public class DailyTransactionImpl implements IDailyTransactionService {

	enum TransactionType {
		OWNER, FEE, REST
	}

	@Autowired
	DailyTransactionRepository dailyTransRepo;
	
	public DailyTransactionImpl() {
		
	}

	@Override
	public CompletableFuture<DailyTransaction> saveData(DailyTransaction t) {
		int remain = t.getTotal() - t.getPaid();
		t.setDaily(t.getCar().getDailyAmount());
		t.setRemain(remain);
		t.setCreated_date(System.currentTimeMillis());
		t.setTransactionCode("Fee" + t.getCreated_date());
		t.setUpdated_date(t.getCreated_date());
		dailyTransRepo.save(t);
		return CompletableFuture.completedFuture(t);
	}

	@Override
	public CompletableFuture<DailyTransaction> updateData(DailyTransaction t) {
		DailyTransaction dt = getDataById(t.getId()).join();
		t.setCreated_date(dt.getCreated_date());
		t.setUpdated_date(System.currentTimeMillis());
		dailyTransRepo.save(t);
		return CompletableFuture.completedFuture(t);
	}

	@Override
	public CompletableFuture<List<DailyTransaction>> getAllDatas() {
		return CompletableFuture.completedFuture(dailyTransRepo.findAll());
	}
	
	@Override
	public CompletableFuture<DailyTransaction> getDataById(long id) {
		DailyTransaction daily = dailyTransRepo
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Daily Transaction not exist" + id));
		return CompletableFuture.completedFuture(daily);
	}

	@Override
	public void deleteById(long id) {
		dailyTransRepo.deleteById(id);
	}
	
}
