package com.ximedes.rb.issuance.api.model.mapping.converter;

import com.ximedes.rb.issuance.api.model.ApiCard;
import com.ximedes.rb.issuance.model.Card;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Convert Card between API domain and application domain for the Orika framework
 */
@Component
public class CardConverter extends BidirectionalConverter<Card, ApiCard> {

    @Override
    public ApiCard convertTo(final Card card, final Type<ApiCard> type) {
        return ApiCard.builder()
                .number(card.getNumber())
                .description(card.getDescription().orElse(null))
                .image(card.getImage())
                .build();
    }

    @Override
    public Card convertFrom(final ApiCard apiCard, final Type<Card> type) {
        return Card.builder()
                .number(apiCard.getNumber())
                .description(Optional.ofNullable(apiCard.getDescription()))
                .image(apiCard.getImage())
                .build();
    }
}
