package com.binhdc.microservice.borrowingservice.command.controller;

import com.binhdc.microservice.borrowingservice.command.command.CreateBorrowCommand;
import com.binhdc.microservice.borrowingservice.command.command.DeleteBorrowCommand;
import com.binhdc.microservice.borrowingservice.command.command.UpdateBookReturnCommand;
import com.binhdc.microservice.borrowingservice.command.data.Borrowing;
import com.binhdc.microservice.borrowingservice.command.model.BorrowRequestModel;
import com.binhdc.microservice.borrowingservice.command.service.IBorrowService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowing")
@EnableBinding(Source.class)
public class BorrowController {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private IBorrowService borrowService;

    @Autowired
    private MessageChannel output;

    @PostMapping
    public String addBookBorrowing(@RequestBody BorrowRequestModel model) {
        try {
            CreateBorrowCommand command =
                    new CreateBorrowCommand(UUID.randomUUID().toString(), model.getBookId(), model.getEmployeeId(), new Date());
            commandGateway.sendAndWait(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Book borrowing added";
    }

    @PutMapping
    public String updateBookReturn(@RequestBody BorrowRequestModel model) {
        Optional<Borrowing> borrowOp = Optional.ofNullable(borrowService.findBorrowingByIdBookId(model.getEmployeeId(), model.getBookId()));
        if (borrowOp.isPresent()) {
            Borrowing borrow = borrowOp.get();
            UpdateBookReturnCommand command = new UpdateBookReturnCommand(borrow.getId(), model.getBookId(), model.getEmployeeId(), new Date());
            commandGateway.sendAndWait(command);
            return "Book returned";
        } else {
            return "Book returned later";
        }
    }

    @DeleteMapping
    public String deleteBorrowing(@RequestBody BorrowRequestModel model) {
        Optional<Borrowing> borrowing = borrowService.findBorrowingById(model.getId());
        if (borrowing.isPresent()) {
            Borrowing borrow = borrowing.get();
            DeleteBorrowCommand command = new DeleteBorrowCommand(borrow.getId());
            commandGateway.sendAndWait(command);
            return "Book deleted";
        } else {
            return "There isn't the book";
        }

    }
}
