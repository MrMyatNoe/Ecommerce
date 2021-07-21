package com.demo.ecom.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.DailyTransaction;
import com.demo.ecom.exception.BadRequestException;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.DailyTransactionRepository;
import com.demo.ecom.service.IDailyTransactionService;
import com.demo.ecom.util.DateTimeUtility;

@Service
public class DailyTransactionImpl implements IDailyTransactionService {

	enum TransactionType {
		OWNER, FEE, REST
	}

	@Autowired
	DailyTransactionRepository dailyTransRepo;

	@Override
	public CompletableFuture<DailyTransaction> saveData(DailyTransaction t) {
		double totalAmount = 0.0;
		switch (t.getStatus()) {
		case "DAILY":
			totalAmount = t.getAmount() - t.getFee();
			break;
		case "FEE":
			totalAmount = t.getFee() - t.getAmount();
			break;
		case "REST":
			totalAmount = 0.0;
			break;
		default:
			throw new BadRequestException("Status must not null");
		}
		t.setTransactionCode(t.getStatus() + DateTimeUtility.dateFormatYearMonthDay());
		t.setTotalAmount(totalAmount);
		t.setCreated_date(System.currentTimeMillis());
		t.setUpdated_date(t.getCreated_date());
		dailyTransRepo.save(t);
		return CompletableFuture.completedFuture(t);
	}

	@Override
	public CompletableFuture<DailyTransaction> updateData(DailyTransaction t) {
		DailyTransaction dt = getDataById(t.getId()).join();
		double totalAmount = 0.0;
		switch (t.getStatus()) {
		case "DAILY":
			totalAmount = t.getAmount() - t.getFee();
			break;
		case "FEE":
			totalAmount = t.getFee() - t.getAmount();
			break;
		case "REST":
			totalAmount = 0.0;
			break;
		default:
			throw new BadRequestException("Status must not null");
		}
		t.setTransactionCode(t.getStatus() + DateTimeUtility.dateFormatYearMonthDay());
		t.setTotalAmount(totalAmount);
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
		this.dailyTransRepo.deleteById(id);
	}
	
}
