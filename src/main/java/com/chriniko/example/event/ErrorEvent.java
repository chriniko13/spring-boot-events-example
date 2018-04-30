package com.chriniko.example.event;

import org.springframework.context.ApplicationEvent;

public class ErrorEvent extends ApplicationEvent {

    private final Throwable error;

    public ErrorEvent(Object source, Throwable error) {
        super(source);
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }
}
