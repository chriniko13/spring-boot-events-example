package com.chriniko.example;

import com.chriniko.example.domain.Numbers;
import com.chriniko.example.domain.Ticket;
import com.chriniko.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private TicketService ticketService;

    public void run(String... args) {
        populateWithTickets();
    }

    private void populateWithTickets() {
        ticketService.removeAll();

        // generate some random tickets...
        IntStream.rangeClosed(1, 10)
                .forEach(idx -> {

                    final int pickSize = 5;
                    List<String> pickedNumbers = new ArrayList<>(pickSize);
                    for (int i = 0; i < pickSize; i++) {
                        pickedNumbers.add(String.valueOf(ThreadLocalRandom.current().nextInt(50) + 1));
                    }

                    Ticket ticket = new Ticket(null, new Numbers(pickedNumbers));
                    ticketService.playTicket(ticket);

                });

        // let's show them now...
        ticketService.findAll().forEach(System.out::println);
    }
}
