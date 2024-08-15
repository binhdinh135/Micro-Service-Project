package com.binhdc.microservice.borrowingservice.command.service;

import com.binhdc.microservice.borrowingservice.command.data.Borrowing;

import java.util.Optional;

public interface IBorrowService {
//    void sendMessage(Message message);
    String findIdBorrowing(String employeeId, String bookId);

    Borrowing findBorrowingByIdBookId(String employee, String bookId);

    Optional<Borrowing> findBorrowingById(String id);
}
