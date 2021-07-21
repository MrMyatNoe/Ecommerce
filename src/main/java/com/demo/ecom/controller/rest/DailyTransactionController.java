package com.demo.ecom.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveTransaction(@RequestBody DailyTransactionRequest request){
		logInfo("save transaction" + request);
		Car car = null;
		Driver driver = null;
		try {
			DailyTransaction dt = new DailyTransaction(request);
			car = carService.getDataById(request.getCarId());
			driver = driverService.getDataById(request.getDriverId());
			dt.setCar(car);
			dt.setDriver(driver);
			return successResponse(dailyTransactionService.saveData(dt));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllDatas(){
		logInfo("Get all daily transactions");
		return successResponse(dailyTransactionService.getAllDatas().join());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	public ResponseEntity<Object> getDataById(@PathVariable("id") Long id){
		logInfo("Get Daily Transaction By Id");
		return successResponse(dailyTransactionService.getDataById(id).join());
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> putData(@RequestBody DailyTransactionRequest request){
		logInfo("edit daily transactions");
		Car car = null;
		Driver driver = null;
		try {
			DailyTransaction dt = new DailyTransaction(request);
			car = carService.getDataById(request.getCarId());
			driver = driverService.getDataById(request.getDriverId());
			dt.setCar(car);
			dt.setDriver(driver);
			return successResponse(dailyTransactionService.updateData(dt).join());
		}catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteData(@RequestParam(name = "id") long id){
		logInfo("delete daily transactions");
		try {
			dailyTransactionService.deleteById(id);
			return deleteSuccessResponse("Delete successful");
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
