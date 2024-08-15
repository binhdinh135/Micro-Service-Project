package com.binhdc.employee.employeeservice.command.data;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
@Setter
@Getter
public class Employee {
    @Id
    private String employeeId;

    private String firstName;

    private String lastName;

    private String kin;

    private Boolean isDisciplined;

}
