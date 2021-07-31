package com.demo.ecom.controller.rest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.ecom.entity.Car;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.CarRequest;
import com.demo.ecom.service.ICarService;
import com.demo.ecom.util.DateTimeUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

@RestController
@RequestMapping("/v1/cars")
public class CarController extends BaseController{

	@Autowired
	ICarService carService;
	
	private String path = DateTimeUtility.path + "/Cars/";	
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllDatas(){
		logInfo("Get All Cars");
		return successResponse(carService.getAllDatas());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getDataById(@PathVariable("id") Long id){
		logInfo("Get Car By Id");
		return successResponse(carService.getDataById(id));
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveDriver(@RequestParam("file") MultipartFile file,
			@RequestParam("car") String car)
			throws JsonMappingException, JsonProcessingException, DemoBasedException {
		File serverFile = null;
		CarRequest c = null;
		try {
			Gson gson = new Gson();
			c = gson.fromJson(car, CarRequest.class);
			
			if(file != null) {
			System.out.println("save car :"+file.getOriginalFilename() + " : " + car );
			
				boolean pathExists = new File(path).exists();
				if (!pathExists) {
					new File(path).mkdir();
				} else {
					new File(path + c.getCarNo()).mkdir();
				}
				
				String originalFileName = file.getOriginalFilename();
				String newFileName = FilenameUtils.getBaseName(originalFileName) + "."
						+ FilenameUtils.getExtension(originalFileName);
				serverFile = new File(path + c.getCarNo()  + File.separator + newFileName);
				try {
					FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("here");
			Car savedCar = new Car(c);
			savedCar.setPhoto(serverFile.toString());
			return successResponse(carService.saveData(savedCar));
		} catch (DemoBasedException e) {
			serverFile.delete();	
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
