package com.demo.ecom.controller.rest;

import com.demo.ecom.entity.Car;
import com.demo.ecom.entity.DailyTransaction;
import com.demo.ecom.entity.Driver;
import com.demo.ecom.entity.Maintenance;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.request.DailyTransactionRequest;
import com.demo.ecom.service.ICarService;
import com.demo.ecom.service.IMaintenanceService;
import com.demo.ecom.util.DateTimeUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //.1
@RequestMapping("/v1/maintenances") //2
public class MaintenanceController extends BaseController{

    @Autowired
    IMaintenanceService maintenanceService;

    @ApiOperation(value = "Get All Maintenances", response = Iterable.class, tags = "getDailyByPageAndSize")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
            @ApiResponse(code = 404, message = "not found!!") })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDailysByPageAndSize(@RequestParam(name = "page") int page,
                                                         @RequestParam(name = "size") int size) {
        try {
            logInfo("Get All Maintenances By Page And Size");
            return successResponse(maintenanceService.getDatasByPageAndSize(page, size));
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTransaction(@RequestBody Maintenance maintenance) {
        logInfo("save Maintenance" + maintenance);
        try {
            return successResponse(maintenanceService.saveData(maintenance));
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }
}
