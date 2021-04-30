package com.demo.ecom.controller.rest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.ecom.entity.Driver;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.IDriverService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/v1/drivers")
public class DriverController extends BaseController {

	@Autowired
	IDriverService driverService;

	@Autowired
	ServletContext context;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResponseEntity<Object> saveDriver(@RequestParam("file") MultipartFile file,
			@RequestParam("driver") String driver)
			throws JsonMappingException, JsonProcessingException, DemoBasedException {
		try {
			Driver d = new ObjectMapper().readValue(driver, Driver.class);
			boolean pathExists = new File(context.getRealPath("/Images")).exists();
			if (!pathExists) {
				new File(context.getRealPath("/Images")).mkdir();
			}
			String originalFileName = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(originalFileName) + "."
					+ FilenameUtils.getExtension(originalFileName);
			File serverFile = new File(context.getRealPath("/Images/") + File.separator + newFileName);
			try {
				FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

			d.setImageName(newFileName);
			return successResponse(driverService.saveData(d));
		} catch (DemoBasedException e) {
			logError(e, e.getMessage());
			return e.response();
		}
	}
}
