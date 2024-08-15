package com.binhdc.bookservice.command.event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDeletedEvent {
    private String bookId;
}
