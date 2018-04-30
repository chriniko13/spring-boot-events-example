package com.chriniko.example.listener;

import com.chriniko.example.event.ErrorEvent;
import com.chriniko.example.repository.ErrorEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.UUID;

@Component
public class ErrorListener {

    @Autowired
    private ErrorEventRepository repository;

    @Async // Note: comment-uncomment to see the behaviour.
    @EventListener(classes = {ErrorEvent.class})
    public void listen(ErrorEvent errorEvent) {

        System.out.println("[" + Thread.currentThread().getName() + "] ErrorListener#listen ---> errorEvent: " + errorEvent.getError());

        repository.save(
                new com.chriniko.example.domain.ErrorEvent(
                        UUID.randomUUID().toString(),
                        extractErrorMessage(errorEvent),
                        extractStackTrace(errorEvent),
                        Instant.now())
        );

    }

    private String extractErrorMessage(ErrorEvent errorEvent) {
        return errorEvent.getError().getMessage();
    }

    private String extractStackTrace(ErrorEvent errorEvent) {

        Throwable error = errorEvent.getError();

        StringWriter sw = new StringWriter();
        error.printStackTrace(new PrintWriter(sw));

        return sw.toString();
    }

}
