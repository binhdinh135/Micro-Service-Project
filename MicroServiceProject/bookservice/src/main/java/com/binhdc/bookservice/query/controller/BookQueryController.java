package com.binhdc.bookservice.query.controller;

import com.binhdc.bookservice.query.model.BookResponseModel;
import com.binhdc.bookservice.query.queries.GetAllBooksQuery;
import com.binhdc.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/app/v1/books")
public class BookQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{bookId}")
    public BookResponseModel getBookDetail(@PathVariable String bookId) {
        GetBookQuery getBooksQuery = new GetBookQuery();
        getBooksQuery.setBookId(bookId);

        BookResponseModel bookResponseModel = queryGateway.query(getBooksQuery,
                                ResponseTypes.instanceOf(BookResponseModel.class)).join();

        return bookResponseModel;
    }

    @GetMapping
    public List<BookResponseModel> getAllBooks(){
        GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();
        List<BookResponseModel> result = queryGateway.query(getAllBooksQuery,
                        ResponseTypes.multipleInstancesOf(BookResponseModel.class))
                .join();
        return result;
    }


}
