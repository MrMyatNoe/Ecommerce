package com.demo.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entity.Category;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController{

	@Autowired
	ICategoryService categoryService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getAllDatas(){
		logInfo("Get All Categories");
		return successResponse(categoryService.getAllDatas());
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveCategory(@RequestBody Category category){
		logInfo("save category");
		try {
			return successResponse(categoryService.saveData(category));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> editCategory(@RequestBody Category category){
		logInfo("edit category");
		try {
			return successResponse(categoryService.saveData(category));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@DeleteMapping
	public synchronized ResponseEntity<Object> deleteCategory(@RequestParam(name = "id") long id){
		logInfo("delete category");
		try {
			categoryService.deleteById(id);
			return deleteSuccessResponse("Delete successful");
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
