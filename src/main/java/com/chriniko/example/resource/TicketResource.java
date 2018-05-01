package com.chriniko.example.resource;

import com.chriniko.example.domain.Ticket;
import com.chriniko.example.dto.PlayTicketDto;
import com.chriniko.example.interceptor.ErrorHandlingAware;
import com.chriniko.example.interceptor.StatisticsAware;
import com.chriniko.example.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
        System.out.println("TicketResource#findAll --- thread: " + Thread.currentThread().getName());
        return ticketService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/async")
    public ResponseEntity findAllAsync() {

        AtomicReference<ResponseEntity> ref = new AtomicReference<>();

        ticketService.findAllAsync()
                .subscribe(
                        list -> {
                            System.out.println("TicketResource#findAllAsync --- thread: " + Thread.currentThread().getName());
                            ref.set(ResponseEntity.ok(list));
                        },
                        error -> ref.set(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                );

        return ref.get();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/async2")
    public DeferredResult<ResponseEntity> findAllAsync2() {

        DeferredResult<ResponseEntity> ref = new DeferredResult<>();

        ticketService.findAllAsync()
                .subscribe(
                        list -> {
                            System.out.println("TicketResource#findAllAsync --- thread: " + Thread.currentThread().getName());
                            ref.setResult(ResponseEntity.ok(list));
                        },
                        error -> ref.setResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                );

        return ref;
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
