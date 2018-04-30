package com.chriniko.example.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.LongAdder;

@Service
public class StatisticsService {

    private LongAdder ticketResourceCounter = new LongAdder();

    public void increaseTicketResourceCounter() {
        ticketResourceCounter.increment();
    }

    public long getTicketResourceCounter() {
        return ticketResourceCounter.sum();
    }

}
