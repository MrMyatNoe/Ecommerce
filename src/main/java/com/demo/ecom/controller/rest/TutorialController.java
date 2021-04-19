package com.demo.ecom.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entity.Tutorial;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.ITutorialService;

@RestController
@RequestMapping("/tutorials")
public class TutorialController extends BaseController {

	@Autowired
	ITutorialService tutoService;

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public synchronized ResponseEntity<Object> getAllDatas(){
//		logInfo("Get All Tutorials");
//		return successResponse(tutoService.getAllDatas());
//	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getDatasByPageAndSize(@RequestParam(required = false) String title,
			@RequestParam (name = "page")int page, @RequestParam(name = "size") int size) {
		try {
			logInfo("Get All Tutorials By title or Page And Size");
			Page<Tutorial> pageTuts = tutoService.getDatasByPageAndSize(page, size);
			Map<String, Object> response = new HashMap<>();
			response.put("tutorials", pageTuts.getContent());
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());
			System.out.println(response);
			return successResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public synchronized ResponseEntity<Object> getDatasByPageAndSize(
//			@RequestParam(required = false) String title,
//			@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "3") int size){
//		try {
//			logInfo("Get All Tutorials By Page And Size");
//			List<Tutorial> tutorials = new ArrayList<Tutorial>();
//			Pageable paging = PageRequest.of(page, size);
//			System.out.println(paging);
//			
//			Page<Tutorial> pageTuts;
//			if(title == null)
//				pageTuts = tutoService.getDatasByPageAndSize(paging);
//			else
//				pageTuts = tutoService.getDatasByTitleAndPaging(title,paging);
//			
//			tutorials = pageTuts.getContent();
//			
//			Map<String,Object> response = new HashMap<>();
//			response.put("tutorials", tutorials);
//			response.put("currentPage", pageTuts.getNumber());
//			response.put("totalItems", pageTuts.getTotalElements());
//			response.put("totalPages", pageTuts.getTotalPages());
//			return successResponse(response);
//		} catch (DemoBasedException e) {
//			logError(e, e.getMessage());
//			return e.response();
//		}
//	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	public synchronized ResponseEntity<Object> getDataById(@PathVariable("id") Long id) {
		logInfo("Get Tutorial By Id");
		return successResponse(tutoService.getDataById(id));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveTutorial(@RequestBody Tutorial tutorial) {
		logInfo("save tutorial");
		try {
			return successResponse(tutoService.saveData(tutorial));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> editCategory(@RequestBody Tutorial tutorial) {
		logInfo("edit category");
		try {
			return successResponse(tutoService.updateData(tutorial));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@DeleteMapping()
	public synchronized ResponseEntity<Object> deleteCategory(@RequestParam(name = "id") long id) {
		logInfo("delete category");
		try {
			tutoService.deleteById(id);
			return deleteSuccessResponse("Delete successful");
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
