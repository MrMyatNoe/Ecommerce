package com.demo.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.Driver;
import com.demo.ecom.exception.BadRequestException;
import com.demo.ecom.exception.NotFoundException;
import com.demo.ecom.repository.DriverRepository;
import com.demo.ecom.service.IDriverService;

@Service
public class DriverServiceImpl implements IDriverService {

	private static final String NRC_REGEX = "\\b([1-9]|1[0-4])[/](Ka|Kh|Ga|Gh|Ng|Ca|Ch|Ja|Jh|Ny|Dd|Nn|Ta|Th|Da|Dh|Na|Pa|Ph|Ba|Bh|Ma|Ya|Ra|La|Wa|Sa|Ha|Ll|u|E|Ah|Na){3}[/](C|AC|NC|V|M|N)[/]\\d{6}\\b\\$";
	private static final String LICENSE_REGEX = "\\b([A-E][/]\\d{5}[/]\\d{2})\\b";

	@Autowired
	DriverRepository driverRepo;
	
	@Autowired
	PasswordEncoder passcodeEncoder;

	@Override
	public List<Driver> getAllDatas() {
		return driverRepo.findAll();
	}

	@Override
	public Driver saveData(Driver d) {
//		if (!d.getNrc().equals(NRC_REGEX)) {
//			throw new FormatException("Please Check your NRC number");
//		}
//		if (!d.getLicense().equals(LICENSE_REGEX)) {
//			throw new FormatException("Please Check your License number");
//		}
		System.out.println("driver in service "+ d);
		d.setCreated_date(System.currentTimeMillis());
		d.setUpdated_date(d.getCreated_date());
		return driverRepo.save(d);
	}

	@Override
	public Driver updateData(Driver d) {
		Driver driver = getDataById(d.getId());
		d.setCreated_date(driver.getCreated_date());
		d.setUpdated_date(System.currentTimeMillis());
		return null;
	}

	@Override
	public void deleteById(long id) {
		getDataById(id);
		this.driverRepo.deleteById(id);
	}

	@Override
	public Driver getDataById(long id) {
		return driverRepo.findById(id).orElseThrow(() -> new NotFoundException("Driver Id not found!"));
	}

	@Override
	public void deleteAll() {
		driverRepo.deleteAll();		
	}

	@Override
	public Driver login(String phone, String password) {
		Driver searchDriver = driverRepo.findByPhone(phone).orElseThrow(() -> new NotFoundException("Driver Not Found! " + phone));
		if(!passcodeEncoder.matches(password, searchDriver.getPassword())) {
			throw new BadRequestException("Wrong Password! Try Again");
		}
		return searchDriver;
	}

	@Override
	public void resetPassword(String phone, String password) {
		Driver searchDriver = driverRepo.findByPhone(phone).orElseThrow(() -> new NotFoundException("Driver Not Found! " + phone));
		searchDriver.setPassword(password);
		searchDriver.setCreated_date(searchDriver.getCreated_date());
		searchDriver.setUpdated_date(System.currentTimeMillis());
		driverRepo.save(searchDriver);		
	}

}
