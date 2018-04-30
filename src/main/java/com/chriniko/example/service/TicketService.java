package com.chriniko.example.service;

import com.chriniko.example.domain.Ticket;
import com.chriniko.example.event.TicketPlayedEvent;
import com.chriniko.example.event.TicketStoredEvent;
import com.chriniko.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.ticketRepository = ticketRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void playTicket(Ticket ticket) {

        ticket.setId(UUID.randomUUID().toString());

        ticket = ticketRepository.save(ticket);

        applicationEventPublisher.publishEvent(new TicketPlayedEvent(this, ticket));

        applicationEventPublisher.publishEvent(new TicketStoredEvent(this, ticket));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void removeAll() {
        ticketRepository.deleteAll();
    }
}
