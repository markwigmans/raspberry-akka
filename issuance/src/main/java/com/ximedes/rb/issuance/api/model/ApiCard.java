package com.ximedes.rb.issuance.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@JsonDeserialize(builder = ApiCard.ApiCardBuilder.class)
@Builder
@Value
public class ApiCard {

    String number;
    String description;
    Byte[] image;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ApiCardBuilder {
    }
}
