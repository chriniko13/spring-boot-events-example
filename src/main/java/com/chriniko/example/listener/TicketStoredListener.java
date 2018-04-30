package com.chriniko.example.listener;

import com.chriniko.example.event.TicketStoredEvent;
import com.chriniko.example.exception.CouldNotHandleEventException;
import com.chriniko.example.interceptor.CodeLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Arrays;
import java.util.Random;

@Component
public class TicketStoredListener {


    @CodeLogger(callMethodWithNoParamsToString = "getTicket", printParamsValues = true)
    @Async // Note: comment-uncomment to see the behaviour.
    @TransactionalEventListener(
            classes = {TicketStoredEvent.class},
            phase = TransactionPhase.AFTER_COMMIT,
            condition = "#ticketStoredEvent.ticket != null")
    public void listen(TicketStoredEvent ticketStoredEvent) {

        System.out.println("[" + Thread.currentThread().getName() + "] ---> TICKET JUST STORED!");

        int randomInt = new Random().nextInt(10) + 1;

        if (Arrays.asList(1, 2, 3, 4).contains(randomInt)) {
            throw new CouldNotHandleEventException("TicketStoredListener#listen");
        }

    }


}
