package com.binhdc.microservice.borrowingservice.command.service;

import com.binhdc.microservice.borrowingservice.command.data.Borrowing;
import com.binhdc.microservice.borrowingservice.command.data.IBorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowService implements IBorrowService {
    @Autowired
    IBorrowRepository repository;

    @Override
    public String findIdBorrowing(String employeeId, String bookId) {
        return repository.findByEmployeeIdAndBookIdAndReturnDateIsNull(employeeId,bookId).getId();

    }

    @Override
    public Borrowing findBorrowingByIdBookId(String employee, String bookId) {
        return repository.findByEmployeeIdAndBookIdAndReturnDateIsNull(employee, bookId);
    }

    @Override
    public Optional<Borrowing> findBorrowingById(String id) {
        return repository.findById(id);
    }


}
