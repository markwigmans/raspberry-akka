package com.ximedes.rb.cards.model;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Optional;

/**
 * Created by mawi on 29/10/2015.
 */
@Value
public class Card {

    String number;
    Optional<String> description;
    List<Integer> image;
}
