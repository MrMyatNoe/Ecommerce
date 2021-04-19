package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Tutorial;
import com.demo.ecom.exception.AlreadyExistsException;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.TutorialRepository;
import com.demo.ecom.service.ITutorialService;

@Service
public class TutorialServiceImpl implements ITutorialService {

	@Autowired
	TutorialRepository tutoRepo;

	@Override
	public List<Tutorial> getAllDatas() {
		return tutoRepo.findAll();
	}

	@Override
	public Page<Tutorial> getDatasByPageAndSize(int page, int size) {
		return tutoRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Tutorial saveData(Tutorial t) {
		if (existByTitle(t.getTitle())) {
			throw new AlreadyExistsException("Tutorial already exists");
		}
		t.setCreated_date(System.currentTimeMillis());
		t.setUpdated_date(t.getCreated_date());
		return tutoRepo.save(t);
	}

	@Override
	public Tutorial updateData(Tutorial t) {
		Tutorial searchTutorial = getDataById(t.getId());
		t.setCreated_date(searchTutorial.getCreated_date());
		t.setUpdated_date(System.currentTimeMillis());
		return tutoRepo.save(t);
	}

	@Override
	public void deleteById(long id) {
		tutoRepo.deleteById(id);
	}

	@Override
	public Tutorial getDataById(long id) {
		return tutoRepo.findById(id).orElseThrow(() -> new NotFoundException("Tutorial Not Found!" + id));

	}

	@Override
	public Tutorial findByTitle(String title) {
		return tutoRepo.findByTitle(title);
	}

	public boolean existByTitle(String title) {
		return findByTitle(title) != null;
	}

}
