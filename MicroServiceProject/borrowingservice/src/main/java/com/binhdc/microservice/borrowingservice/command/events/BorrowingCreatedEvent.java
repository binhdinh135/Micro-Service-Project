package com.binhdc.microservice.borrowingservice.command.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingCreatedEvent {

    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
}
