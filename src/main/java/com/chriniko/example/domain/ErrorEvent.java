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

@Document(collection = "error-events")
public class ErrorEvent {

    @Id
    private String eventId;

    private String errorMessage;
    private String errorStacktrace;

    private Instant creationTime;
}
