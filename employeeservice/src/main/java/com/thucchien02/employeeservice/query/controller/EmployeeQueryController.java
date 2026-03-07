package com.thucchien02.employeeservice.query.controller;

import com.thucchien02.commonservice.model.EmployeeResponseCommonModel;
import com.thucchien02.employeeservice.query.model.EmployeeResponseModel;
import com.thucchien02.employeeservice.query.queries.GetAllEmployeeQuery;
import com.thucchien02.commonservice.queries.GetDetailEmployeeQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee(@RequestParam(required = false, defaultValue = "false") Boolean isDisciplined){
        log.info("Calling to getAllEmployee");
        return queryGateway
                .query(new GetAllEmployeeQuery(isDisciplined), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseCommonModel getDetailEmployee(@PathVariable String employeeId){
        return queryGateway.query(new GetDetailEmployeeQuery(employeeId),ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();
    }
}
