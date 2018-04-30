package com.chriniko.example.listener;

import com.chriniko.example.domain.PlayedTicketEvent;
import com.chriniko.example.event.TicketPlayedEvent;
import com.chriniko.example.repository.TicketPlayedEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TicketPlayedListener implements ApplicationListener<TicketPlayedEvent> {

    private static final String DASH_LINE = "===================================";
    private static final String NEXT_LINE = "\n";

    private final TicketPlayedEventRepository ticketPlayedEventRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TicketPlayedListener(TicketPlayedEventRepository ticketPlayedEventRepository, ObjectMapper objectMapper) {
        this.ticketPlayedEventRepository = ticketPlayedEventRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onApplicationEvent(TicketPlayedEvent event) {

        try {

            String numbersAsString = objectMapper.writeValueAsString(event.getTicket().getNumbers().getChoice());

            PlayedTicketEvent playedTicketEvent = new PlayedTicketEvent(UUID.randomUUID().toString(),
                    event.getTicket().getId(),
                    numbersAsString,
                    Instant.now());

            ticketPlayedEventRepository.save(playedTicketEvent);

            Object obj = event.getSource();

            String str = NEXT_LINE
                    + DASH_LINE
                    + NEXT_LINE
                    + "  Class: " + obj.getClass().getSimpleName()
                    + NEXT_LINE
                    + "Message: "
                    + event
                    + NEXT_LINE
                    + "Value: "
                    + event.getTicket()
                    + NEXT_LINE
                    + DASH_LINE;

            System.out.println(str);
        } catch (Exception error) {
            System.err.println("TicketPlayedListener#onApplicationEvent --- Critical error occurred!");
            throw new IllegalStateException(error);
        }
    }
}
