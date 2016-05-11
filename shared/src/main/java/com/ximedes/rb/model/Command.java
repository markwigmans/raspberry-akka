package com.ximedes.rb.model;

import java.time.LocalDateTime;

/**
 * Created by mawi on 01/11/2015.
 */
public abstract class Command {

    private final LocalDateTime received;

    public Command() {
        received = LocalDateTime.now();
    }

    public Command(final LocalDateTime received) {
        this.received = received;
    }

    public LocalDateTime getReceived() {
        return received;
    }
}
