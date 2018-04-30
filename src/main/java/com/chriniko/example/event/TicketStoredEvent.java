package com.chriniko.example.event;

import com.chriniko.example.domain.Ticket;
import org.springframework.context.ApplicationEvent;

public class TicketStoredEvent extends ApplicationEvent {

    private final Ticket ticket;

    public TicketStoredEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
