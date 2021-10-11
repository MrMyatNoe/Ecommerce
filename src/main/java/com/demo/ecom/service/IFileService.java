package com.demo.ecom.service;

import org.springframework.core.io.Resource;

public interface IFileService {

    byte[] load(String fullPath) throws Exception;
    Resource noImageLoad();
    Resource loadTest(String string);
}
