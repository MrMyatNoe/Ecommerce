package com.demo.ecom.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.demo.ecom.exception.BadRequestException;
import com.demo.ecom.service.IFileService;
import com.demo.ecom.util.DateTimeUtility;

@Service
public class FileServiceImpl implements IFileService {

    private final Path root = Paths.get(DateTimeUtility.testPath);

    private final Path localRoot = Paths.get("src/main/resources/static/images");
    @Override
    public byte[] load(String fullPath) throws Exception {
        File file = new File("/home/tmn/git/Ecommerce/src/main/resources/static/images/"+ fullPath);
       // File file = new File("/home/tmn/public/Ecommerce/Images/Drivers/U Pyaung/SkypeAccount.png");
        System.out.println("file "+ file);
        return IOUtils.toByteArray(new FileInputStream(file));
    }
    
    public Resource loadTest(String fullPath) {
        try {
            System.out.println("load test"+ root + "/" + fullPath);
            Path file = root.resolve(fullPath);
            Resource resource = new UrlResource(file.toUri());
            return resource;
        } catch (MalformedURLException e) {
            throw new BadRequestException("Error: " + e.getMessage());
        }
    }

    @Override
    public Resource noImageLoad() {
        try {
            System.out.println("no image load"+ localRoot );
            Path file = localRoot.resolve("No_Image_Available.jpg");
            Resource resource = new UrlResource(file.toUri());
            return resource;
        } catch (MalformedURLException e) {
            throw new BadRequestException("Error: " + e.getMessage());
        }
    }

}
