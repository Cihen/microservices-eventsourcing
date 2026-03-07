package com.thucchien02.employeeservice.command.controller;

import com.thucchien02.employeeservice.command.commands.CreateEmployeeCommand;
import com.thucchien02.employeeservice.command.commands.DeleteEmployeeCommand;
import com.thucchien02.employeeservice.command.commands.UpdateEmployeeCommand;
import com.thucchien02.employeeservice.command.model.CreateEmployeeModel;
import com.thucchien02.employeeservice.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel model){
        CreateEmployeeCommand command =
                new CreateEmployeeCommand(UUID.randomUUID().toString(),model.getFirstName(),model.getLastName(),model.getKin(),false);
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String updateEmployee(@Valid @RequestBody UpdateEmployeeModel model, @PathVariable String employeeId){
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId,model.getFirstName(),model.getLastName(),model.getKin(),model.getIsDisciplined());
        return commandGateway.sendAndWait(command);
    }


    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }
}
