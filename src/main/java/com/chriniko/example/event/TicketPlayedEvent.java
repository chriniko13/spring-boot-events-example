package com.chriniko.example.event;

import com.chriniko.example.domain.Ticket;
import org.springframework.context.ApplicationEvent;

public class TicketPlayedEvent extends ApplicationEvent {

    private final Ticket ticket;

    public TicketPlayedEvent(Object source, Ticket ticket) {
        super(source);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

}
