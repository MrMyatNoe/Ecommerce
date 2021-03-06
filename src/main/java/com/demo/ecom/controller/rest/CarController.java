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
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.ICarService;

@RestController
@RequestMapping("/v1/cars")
public class CarController extends BaseController{

	@Autowired
	ICarService carService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getAllDatas(){
		logInfo("Get All Cars");
		return successResponse(carService.getAllDatas());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public synchronized ResponseEntity<Object> getDataById(@PathVariable("id") Long id){
		logInfo("Get Car By Id");
		return successResponse(carService.getDataById(id));
	}
	
	@RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveCar(@RequestBody Car car){
		logInfo("save Car");
		try {
			return successResponse(carService.saveData(car));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> editCar(@RequestBody Car car){
		logInfo("edit car");
		try {
			return successResponse(carService.updateData(car));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> deleteCar(@RequestParam(name = "id") long id){
		logInfo("delete car");
		try {
			carService.deleteById(id);
			return deleteSuccessResponse("Delete successful");
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
