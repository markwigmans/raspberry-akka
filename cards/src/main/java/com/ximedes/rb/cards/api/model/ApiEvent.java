package com.ximedes.rb.cards.api.model;

import java.time.LocalDateTime;

/**
 * Created by mawi on 01/11/2015.
 */
public abstract class ApiEvent {

    private final LocalDateTime timestamp = LocalDateTime.now();
}
