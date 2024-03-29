package com.demo.ecom.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.entity.Car;
import com.demo.ecom.entity.DailyTransaction;
import com.demo.ecom.entity.Driver;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.DailyTransactionRequest;
import com.demo.ecom.service.ICarService;
import com.demo.ecom.service.IDailyTransactionService;
import com.demo.ecom.service.IDriverService;
import com.demo.ecom.util.DateTimeUtility;

@RestController
@RequestMapping("/v1/dailyTransactions")
public class DailyTransactionController extends BaseController {

    @Autowired
    IDailyTransactionService dailyTransactionService;

    @Autowired
    ICarService carService;

    @Autowired
    IDriverService driverService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllDatas(){
        logInfo("Get All Daily");
        List<DailyTransaction> list = dailyTransactionService.getAllDatas();
        list.stream()
            .forEach(dailyTransaction -> 
                    { dailyTransaction.setCarNo(dailyTransaction.getCar().getCarNo());
                      dailyTransaction.setDriverName(dailyTransaction.getDriver().getName()); 
                     });
        return successResponse(list);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTransaction(@RequestBody DailyTransactionRequest request) {
        logInfo("save transaction" + request);
        Car car = null;
        Driver driver = null;
        try {
            DailyTransaction dt = new DailyTransaction();
            car = carService.getDataById(request.getCarId());
            driver = driverService.getDataById(request.getDriverId());
            dt.setCar(car);
            dt.setDriver(driver);
            dt.setPaid(request.getPaid());
            dt.setDays(request.getDay());
            dt.setTotal(request.getTotal());
            dt.setStartedDate(request.getStartedDate());
            if(request.getRemark().equals("") || request.getRemark()==null)
                dt.setRemark(DateTimeUtility.remarkForDaily);
            else
                dt.setRemark(request.getRemark());
            dt.setEndDate(request.getEndDate());
            return successResponse(dailyTransactionService.saveData(dt));
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }

    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Object> getDataById(@PathVariable("id") Long id) {
        logInfo("Get Daily Transaction By Id");
        return successResponse(dailyTransactionService.getDataById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> putData(@RequestBody DailyTransactionRequest request) {
        logInfo("edit daily transactions");
        try {
            DailyTransaction dt = new DailyTransaction();
            Car car = carService.getDataById(request.getCarId());
            Driver driver = driverService.getDataById(request.getDriverId());
            dt.setCar(car);
            dt.setDriver(driver);
            return successResponse(dailyTransactionService.updateData(dt));
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteData(@RequestParam(name = "id") long id) {
        logInfo("delete daily transactions");
        try {
            dailyTransactionService.deleteById(id);
            return deleteSuccessResponse("Delete successful");
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }
}











