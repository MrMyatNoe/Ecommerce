package com.demo.ecom.controller.rest;

import com.demo.ecom.entity.Leave;
import com.demo.ecom.exception.DemoBasedException;
import com.demo.ecom.service.ILeaveService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //.1
@RequestMapping("/v1/leaves") //2
public class LeaveController extends BaseController{

    @Autowired
    ILeaveService leaveService;

    @ApiOperation(value = "Get All Leaves", response = Iterable.class, tags = "getLeavesByPageAndSize")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!"),
            @ApiResponse(code = 404, message = "not found!!") })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDailysByPageAndSize(@RequestParam(name = "page") int page,
                                                         @RequestParam(name = "size") int size) {
        try {
            logInfo("Get All Leaves By Page And Size");
            return successResponse(leaveService.getDatasByPageAndSize(page, size));
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveTransaction(@RequestBody Leave leave) {
        logInfo("save Leave" + leave);
        try {
            return successResponse(leaveService.saveData(leave));
        } catch (DemoBasedException e) {
            logError(e, e.getMessage());
            return e.response();
        }
    }
}
