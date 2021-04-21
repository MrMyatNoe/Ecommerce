package com.demo.ecom.service;

import org.springframework.data.domain.Page;

import com.demo.ecom.entity.Tutorial;

public interface ITutorialService extends IBaseService<Tutorial> {

	Tutorial findByTitle(String title);

	Page<Tutorial> getDatasByPageAndSize(int page, int size);
	
	Page<Tutorial> getDatasByTitlePageSize(String title, int page, int size);
	
	Long getPublishedCounts();
	
	Long getPendingCounts();
}
