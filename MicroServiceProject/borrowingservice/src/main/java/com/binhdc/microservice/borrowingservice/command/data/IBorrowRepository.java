package com.binhdc.microservice.borrowingservice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBorrowRepository extends JpaRepository<Borrowing, String> {

    Borrowing findByEmployeeIdAndBookIdAndReturnDateIsNull(String employeeId, String bookId);

}
