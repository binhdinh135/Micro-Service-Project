package com.binhdc.microservice.borrowingservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Date;

@Data
@AllArgsConstructor
public class CreateBorrowCommand {

    @TargetAggregateIdentifier
    private String id;

    private String bookId;
    private String employeeId;
    private Date borrowingDate;

}
