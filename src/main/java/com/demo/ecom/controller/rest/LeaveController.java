package com.demo.ecom.controller.rest;

import com.demo.ecom.entity.Leave;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.ILeaveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //.1
@RequestMapping("/v1/leaves") //2
public class LeaveController extends BaseController{

    @Autowired
    ILeaveService leaveService;

    @ApiOperation(value = "Get All Maintenances", response = Iterable.class, tags = "getMaintencesByPageAndSize")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
            @ApiResponse(code = 404, message = "not found!!") })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllDatas(){
        logInfo("Get All Maintenances");
        List<Leave> list = leaveService.getAllDatas().join();
        list.stream()
            .forEach(leave -> {
                     leave.setCarNo(leave.getCar().getCarNo());
                     leave.setDriverName(leave.getDriver().getName());
            });
        return successResponse(list);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveLeave(@RequestBody Leave leave) {
        logInfo("save Leave" + leave);
        try {
            return successResponse(leaveService.saveData(leave));
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }
}
