package com.ximedes.rb.model;

import java.time.LocalDateTime;

/**
 * Created by mawi on 01/11/2015.
 */
public abstract class Event {

    private final LocalDateTime created;

    public Event() {
        created = LocalDateTime.now();
    }

    public Event(final LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
