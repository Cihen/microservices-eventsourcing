package com.thucchien02.employeeservice.command.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeCommand {

    @TargetAggregateIdentifier
    private String id;
    private String firstName;
    private String LastName;
    private String Kin;
    private Boolean isDisciplined;
}
