package com.thucchien02.bookservice.command.aggregate;

import com.thucchien02.bookservice.command.commands.CreateBookCommand;
import com.thucchien02.bookservice.command.commands.DeleteBookCommand;
import com.thucchien02.bookservice.command.commands.UpdateBookCommand;
import com.thucchien02.bookservice.command.event.BookCreateEvent;
import com.thucchien02.bookservice.command.event.BookDeleteEvent;
import com.thucchien02.bookservice.command.event.BookUpdateEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand command){
        BookCreateEvent bookCreatedEvent = new BookCreateEvent();
        BeanUtils.copyProperties(command,bookCreatedEvent);

        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @CommandHandler
    public void updateBook (UpdateBookCommand command) {
        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(command, bookUpdateEvent);

        AggregateLifecycle.apply(bookUpdateEvent);
    }

    @CommandHandler
    public void deleteBook (DeleteBookCommand command) {
        BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(command, bookDeleteEvent);

        AggregateLifecycle.apply(bookDeleteEvent);
    }

    @EventSourcingHandler
    public void on (BookCreateEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on (BookUpdateEvent event) {
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on (BookDeleteEvent event) {
        this.id = event.getId();
    }
}
