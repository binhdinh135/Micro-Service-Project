package com.binhdc.bookservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Setter
@Getter
@AllArgsConstructor
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String bookId;

}
