package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.DailyTransaction;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.DailyTransactionRepository;
import com.demo.ecom.service.IDailyTransactionService;

@Service
public class DailyTransactionImpl implements IDailyTransactionService {

	@Autowired
	DailyTransactionRepository dailyTransRepo;
	
	@Override
	public DailyTransaction saveData(DailyTransaction t) {
		int remain = t.getTotal() - t.getPaid();
		t.setDaily(t.getCar().getDailyAmount());
		t.setRemain(remain);
		t.setCreated_date(System.currentTimeMillis());
		t.setTransactionCode("Fee" + t.getCreated_date());
		t.setUpdated_date(t.getCreated_date());
		return dailyTransRepo.save(t);
		//return CompletableFuture.completedFuture(t);
	}

	@Override
	public DailyTransaction updateData(DailyTransaction t) {
		DailyTransaction dt = getDataById(t.getId());
		t.setCreated_date(dt.getCreated_date());
		t.setUpdated_date(System.currentTimeMillis());
		return dailyTransRepo.save(t);
		//return CompletableFuture.completedFuture(t);
	}

	@Override
	public List<DailyTransaction> getAllDatas() {
		return dailyTransRepo.findAll();
	}
	
	@Override
	public DailyTransaction getDataById(long id) {
		//DailyTransaction daily = 
		return dailyTransRepo
				.findById(id)
				.orElseThrow(() -> new NotFoundException("Daily Transaction not exist" + id));
		//return CompletableFuture.completedFuture(daily);
	}

	@Override
	public void deleteById(long id) {
		getDataById(id);
		dailyTransRepo.deleteById(id);
	}

    @Override
    public List<DailyTransaction> getDatasByPageAndSize(int page, int size) {
        System.out.println(page + " : "+ size);
        Page<DailyTransaction> pages = dailyTransRepo.findAll(PageRequest.of(page, size));
   
        List<DailyTransaction> list = pages.getContent();
		list.stream().forEach(dailyTransaction -> { dailyTransaction.setCarNo(dailyTransaction.getCar().getCarNo());
			dailyTransaction.setDriverName(dailyTransaction.getDriver().getName()); });
        return list;
    }

    @Override
    public Page<DailyTransaction> getDailysByPageAndSize(int page, int size) {
        return dailyTransRepo.findAll(PageRequest.of(page, size));
    }
	
}
