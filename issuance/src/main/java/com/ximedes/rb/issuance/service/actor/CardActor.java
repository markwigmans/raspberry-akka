package com.ximedes.rb.issuance.service.actor;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.ximedes.rb.issuance.model.Card;
import com.ximedes.rb.model.Command;
import com.ximedes.rb.model.Event;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;


/**
 * Created by mawi on 31/10/2015.
 */
public class CardActor extends AbstractLoggingActor {

    /**
     * Create Props for an actor of this type.
     */
    public static Props props() {
        return Props.create(CardActor.class, CardActor::new);
    }

    private CardActor() {
        receive(ReceiveBuilder
                .match(CardActor.Create.class, this::processCreate)
                .matchAny(o -> log().warning("received unknown message: {}", o)).build());
    }

    void processCreate(final CardActor.Create cc) {
        log().debug("Received message: {}", cc);
        final CommandProcessed event = CommandProcessed.builder()
                .card(cc.getCard())
                .command(cc)
                .build();
        sender().tell(event, self());
    }

    @Builder
    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class Create extends Command {
        Card card;
    }

    @Builder
    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class CommandProcessed extends Event {
        Card card;
        Command command;
    }
}
