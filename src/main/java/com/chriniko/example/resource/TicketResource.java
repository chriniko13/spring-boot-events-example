package com.chriniko.example.resource;

import com.chriniko.example.domain.Ticket;
import com.chriniko.example.dto.PlayTicketDto;
import com.chriniko.example.interceptor.ErrorHandlingAware;
import com.chriniko.example.interceptor.StatisticsAware;
import com.chriniko.example.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@StatisticsAware(resourceName = "ticket-resource")

@RestController
@RequestMapping("/tickets")
public class TicketResource {

    private final TicketService ticketService;
    private final ModelMapper modelMapper;

    @Autowired
    public TicketResource(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    //@StatisticsAware(resourceName = "ticket-resource")
    @RequestMapping(method = RequestMethod.GET)
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    public void buyTicket(@RequestBody PlayTicketDto playTicketDto) {
        Ticket ticketToPlay = modelMapper.map(playTicketDto, Ticket.class);
        ticketService.playTicket(ticketToPlay);
    }

    @ErrorHandlingAware
    @RequestMapping(method = RequestMethod.GET, path = "/simulate-error")
    public void simulateErrorCase() {
        throw new IllegalStateException();
    }

}
