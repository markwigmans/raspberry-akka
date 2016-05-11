package com.ximedes.rb.issuance.model;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Created by mawi on 29/10/2015.
 */
@Builder
@Value
public class Card {

    String number;
    Optional<String> description;
    Byte[] image;

    public Card(final String number, final Optional<String> description, final Byte[] image) {
        this.number = number;
        this.description = description;
        this.image = image;
    }
}
