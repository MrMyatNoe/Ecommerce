package com.demo.ecom.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entity.Tutorial;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.ITutorialService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/v1/tutorials")
public class TutorialController extends BaseController {

	@Autowired
	ITutorialService tutoService;

//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//	public synchronized ResponseEntity<Object> getAllDatas(){
//		logInfo("Get All Tutorials");
//		return successResponse(tutoService.getAllDatas());
//	}

	@ApiOperation(value = "Get All Tutorials", response = Iterable.class, tags = "getTutotrialsByPageAndSize")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> getTutotrialsByPageAndSize(@RequestParam(required = false) String title,
			@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
		try {
			logInfo("Get All Tutorials By title or Page And Size");
			Page<Tutorial> pageTuts;
			if (title == null)
				pageTuts = tutoService.getDatasByPageAndSize(page, size);
			else
				pageTuts = tutoService.getDatasByTitlePageSize(title, page, size);
			Map<String, Object> response = new HashMap<>();
			response.put("tutorials", pageTuts.getContent());
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());
			return successResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@ApiOperation(value = "Get Tutorial by Id", response = Tutorial.class, tags = "getTutorialById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
	public synchronized ResponseEntity<Object> getTutorialById(@PathVariable("id") Long id) {
		logInfo("Get Tutorial By Id");
		return successResponse(tutoService.getDataById(id));
	}

	@ApiOperation(value = "Save Tutorial", response = Tutorial.class, tags = "saveTutorial")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveTutorial(@RequestBody Tutorial tutorial) {
		logInfo("save tutorial");
		try {
			return successResponse(tutoService.saveData(tutorial));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@ApiOperation(value = "Update Tutorial", response = Tutorial.class, tags = "updateTutorial")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> updateTutorial(@RequestBody Tutorial tutorial) {
		logInfo("edit tutorial");
		try {
			return successResponse(tutoService.updateData(tutorial));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@ApiOperation(value = "Delete Tutorial", response = String.class, tags = "deleteTutorial")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> deleteTutorial(@RequestParam(name = "id") long id) {
		logInfo("delete category");
		try {
			tutoService.deleteById(id);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Delete Successful");
			return deleteSuccessResponse(response);
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}

	@ApiOperation(value = "Get Tutorial Published", response = Tutorial.class, tags = "Get Published Tutorial")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/published")
	public synchronized ResponseEntity<Object> getPublishedCounts() {
		logInfo("Published Count");
		return successResponse(tutoService.getPublishedCounts());
	}

	@ApiOperation(value = "Get Tutorial Pending", response = Tutorial.class, tags = "Get Pending Tutorial")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
			@ApiResponse(code = 404, message = "not found!!") })
	@GetMapping(path = "/pending")
	public synchronized ResponseEntity<Object> getPendingCounts() {
		logInfo("Pending Count");
		return successResponse(tutoService.getPendingCounts());
	}
}