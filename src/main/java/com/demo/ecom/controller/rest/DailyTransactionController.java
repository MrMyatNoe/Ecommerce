package com.demo.ecom.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entity.Car;
import com.demo.ecom.entity.DailyTransaction;
import com.demo.ecom.entity.Driver;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.DailyTransactionRequest;
import com.demo.ecom.service.ICarService;
import com.demo.ecom.service.IDailyTransactionService;
import com.demo.ecom.service.IDriverService;

@RestController
@RequestMapping("/v1/dailytrans")
public class DailyTransactionController extends BaseController{
	
	@Autowired
	IDailyTransactionService dailyTransactionService;
	
	@Autowired
	ICarService carService;
	
	@Autowired
	IDriverService driverService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveTransaction(@RequestBody DailyTransactionRequest request){
		logInfo("save transaction" + request);
		try {
			DailyTransaction dt = new DailyTransaction(request);
			Car car = carService.getDataById(request.getCarId());
			Driver driver = driverService.getDataById(request.getDriverId());
			dt.setCar(car);
			dt.setDriver(driver);
			return successResponse(dailyTransactionService.saveData(dt));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
		
	}

}
