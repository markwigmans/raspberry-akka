package com.ximedes.rb.cards.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Created by mawi on 29/10/2015.
 */
@JsonDeserialize(builder = ApiCard.ApiCardBuilder.class)
@Builder
@Value
public class ApiCard {

    String number;
    String description;
    int[] image;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ApiCardBuilder {
    }
}
