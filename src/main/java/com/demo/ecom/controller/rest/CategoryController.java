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

import com.demo.ecom.entity.Category;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.ICategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController extends BaseController{

	@Autowired
	ICategoryService categoryService;
	
	@ApiOperation(value = "Get All Categories",response = Iterable.class, tags = "getAllCategories")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!")
	})
	@RequestMapping(method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getAllCategories(){
		logInfo("Get All Categories");
		return successResponse(categoryService.getAllDatas());
	}
	
	@ApiOperation(value = "Get Category By Id",response = Category.class, tags = "getCategoryById")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!")
	})
	@RequestMapping(method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public synchronized ResponseEntity<Object> getCategoryById(@PathVariable("id") Long id){
		logInfo("Get Category By Id");
		return successResponse(categoryService.getDataById(id));
	}
	
	@ApiOperation(value = "Save Category",response = Category.class, tags = "saveCategory")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!")
	})
	@RequestMapping(method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ADMIN')")
	public synchronized ResponseEntity<Object> saveCategory(@RequestBody Category category){
		logInfo("save category");
		try {
			return successResponse(categoryService.saveData(category));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@ApiOperation(value = "Update Category",response = Category.class, tags = "updateCategory")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!")
	})
	@RequestMapping(method = RequestMethod.PUT ,produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ADMIN')")
	public synchronized ResponseEntity<Object> updateCategory(@RequestBody Category category){
		logInfo("edit category");
		try {
			return successResponse(categoryService.updateData(category));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
	
	@ApiOperation(value = "Delete Category",response = String.class, tags = "deleteCategoryById")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete Successful"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!")
	})
	
	@RequestMapping(method = RequestMethod.DELETE ,produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('ADMIN')")
	public synchronized ResponseEntity<Object> deleteCategoryById(@RequestParam(name = "id") long id){
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
