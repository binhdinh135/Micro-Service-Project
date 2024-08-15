package com.binhdc.employee.employeeservice.command.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDeletedEvent {
    private String employeeId;

}
