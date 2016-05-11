package com.ximedes.rb.cards.api.model.mapping.converter;

import com.google.common.primitives.Ints;
import com.ximedes.rb.cards.api.model.ApiCard;
import com.ximedes.rb.cards.model.Card;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

/**
 * Convert Card between API domain and application domain for the Orika framework
 */
public class CardConverter extends BidirectionalConverter<Card, ApiCard> {

    @Override
    public ApiCard convertTo(final Card card, final Type<ApiCard> type) {
        return ApiCard.builder()
                .number(card.getNumber())
                .description(card.getDescription().orElse(null))
                .image(Ints.toArray(card.getImage()))
                .build();
    }

    @Override
    public Card convertFrom(final ApiCard apiCard, final Type<Card> type) {
        return new Card(apiCard.getNumber(), Optional.ofNullable(apiCard.getDescription()),
                apiCard.getImage() != null ? Ints.asList(apiCard.getImage()) : Collections.emptyList());
    }
}
