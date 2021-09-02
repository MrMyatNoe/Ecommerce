package com.demo.ecom.controller.rest;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.service.IFileService;

@RequestMapping("v1/files")
@RestController
public class FileController extends BaseController{

    @Autowired
    IFileService fileService;
   
    /***
     * 
     * @param filename ( filename with extension .jpg)
     * @return if filename exists return file image, if not No Image Available.JPG
     * @throws IOException
     */
    @RequestMapping(path = "/images/drivers/{name}/{filename}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public synchronized byte[] loadImage(@PathVariable String name, @PathVariable String filename) throws IOException {
        logInfo("Entering get image file: " + filename);
        try {
            return fileService.load("Drivers"+ "/" + name + "/" + filename);
        } catch (Exception e) {
            e.printStackTrace();
            Resource file = fileService.noImageLoad();
            return IOUtils.toByteArray(file.getInputStream());
        }
    }
}
