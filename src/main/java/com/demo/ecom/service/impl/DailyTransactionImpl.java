package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.DailyTransaction;
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
	public List<DailyTransaction> getAllDatas() {
		return dailyTransRepo.findAll();
	}

	@Override
	public DailyTransaction saveData(DailyTransaction t) {
	t.setTransactionCode(t.getStatus() + DateTimeUtility.dateFormatYearMonthDay());
	
	double totalAmount = 0.0;
	if (t.getStatus().equalsIgnoreCase(TransactionType.OWNER.toString()) && t.getAmount() > t.getFee()) {
		totalAmount = t.getAmount() - t.getFee();
	}
	
	if (t.getStatus().equalsIgnoreCase(TransactionType.FEE.toString()) && t.getFee() > t.getAmount()) {
		totalAmount = t.getFee() - t.getAmount();
	}
	
	if(t.getStatus().equalsIgnoreCase(TransactionType.REST.toString())) {
		totalAmount = 0.0;
	}
//	if(t.getAmount() > t.getFee()) {
//		totalAmount = t.getAmount() - t.getFee();
//	}
//	if(t.getFee() > t.getAmount()) {
//		totalAmount = t.getFee() - t.getAmount();
//	}
//			//double
	//totalAmount = (t.getAmount() > t.getFee() ? t.getAmount() - t.getFee() : t.getFee() - t.getAmount());	
	t.setTotalAmount(totalAmount);
	t.setCreated_date(System.currentTimeMillis());
	t.setUpdated_date(t.getCreated_date());
	
	System.out.println("In service : "+ t);
	return dailyTransRepo.save(t);
	}

	@Override
	public DailyTransaction updateData(DailyTransaction t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public DailyTransaction getDataById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
