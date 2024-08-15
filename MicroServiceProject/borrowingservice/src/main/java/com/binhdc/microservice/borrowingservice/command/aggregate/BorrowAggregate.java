package com.binhdc.microservice.borrowingservice.command.aggregate;

import com.binhdc.microservice.borrowingservice.command.command.CreateBorrowCommand;
import com.binhdc.microservice.borrowingservice.command.command.DeleteBorrowCommand;
import com.binhdc.microservice.borrowingservice.command.command.UpdateBookReturnCommand;
import com.binhdc.microservice.borrowingservice.command.events.BorrowingCreatedEvent;
import com.binhdc.microservice.borrowingservice.command.events.BorrowingDeletedEvent;
import com.binhdc.microservice.borrowingservice.command.events.BorrowingUpdateBookReturnEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
public class BorrowAggregate {

    @AggregateIdentifier
    private String id;

    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;

    private String message;
    public BorrowAggregate() {}

    @CommandHandler
    public BorrowAggregate(CreateBorrowCommand command) {
        BorrowingCreatedEvent event = new BorrowingCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);

    }

    @CommandHandler
    public void handle(UpdateBookReturnCommand command) {
        BorrowingUpdateBookReturnEvent event = new BorrowingUpdateBookReturnEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteBorrowCommand command) {
        BorrowingDeletedEvent event = new BorrowingDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }


    @EventSourcingHandler
    public void on(BorrowingCreatedEvent event) {
        this.bookId = event.getBookId();
        this.borrowingDate = event.getBorrowingDate();
        this.employeeId = event.getEmployeeId();
        this.id = event.getId();

    }

    @EventSourcingHandler
    public void on(BorrowingUpdateBookReturnEvent event) {
        this.returnDate = event.getReturnDate();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployee();
    }

    @EventSourcingHandler
    public void on(DeleteBorrowCommand event) {
        this.id = event.getId();
    }
}
