package com.chriniko.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"eventId"})
@ToString

@Document(collection = "ticket-played-events")
public class PlayedTicketEvent {

    @Id
    private String eventId;

    private String ticketId;
    private String ticketNumbers;

    private Instant creationTime;


}
