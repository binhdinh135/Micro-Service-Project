package com.binhdc.microservice.borrowingservice.command.events;

import com.binhdc.microservice.borrowingservice.command.data.Borrowing;
import com.binhdc.microservice.borrowingservice.command.data.IBorrowRepository;
import com.binhdc.microservice.borrowingservice.command.service.IBorrowService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BorrowingEventsHandler {
    @Autowired
    private IBorrowRepository borrowRepository;

    @Autowired
    private IBorrowService borrowService;

    @EventHandler
    public void on(BorrowingCreatedEvent event) {
        Borrowing model = new Borrowing();
        BeanUtils.copyProperties(event, model);

        borrowRepository.save(model);
    }

    @EventHandler
    public void on(BorrowingUpdateBookReturnEvent event) {
        Borrowing model = borrowService.findBorrowingByIdBookId(event.getEmployee(), event.getBookId());
        model.setReturnDate(event.getReturnDate());
        borrowRepository.save(model);
    }

    @EventHandler
    public void on(BorrowingDeletedEvent event) {
        Optional<Borrowing> optional = borrowService.findBorrowingById(event.getId());
        if (optional.isPresent()) {
            Borrowing model = optional.get();
            borrowRepository.delete(model);
        }
    }

}
