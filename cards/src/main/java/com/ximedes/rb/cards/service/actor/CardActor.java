package com.ximedes.rb.cards.service.actor;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.google.common.collect.Maps;
import com.ximedes.rb.cards.model.Card;
import com.ximedes.rb.model.Command;
import com.ximedes.rb.model.Event;
import com.ximedes.rb.model.ValueCommand;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.*;
import java.util.Optional;


/**
 * Created by mawi on 31/10/2015.
 */
public class CardActor extends AbstractLoggingActor {

    public static final Command Reset = new ValueCommand("RESET");

    private final Map<String, Card> cards = Maps.newHashMap();

    /**
     * Create Props for an actor of this type.
     */
    public static Props props() {
        return Props.create(CardActor.class, CardActor::new);
    }

    private CardActor() {
        receive(ReceiveBuilder
                .match(CardActor.Retrieve.class, this::process)
                .match(CardActor.Create.class, this::process)
                .match(CardActor.ReverseImage.class, this::process)
                .match(ValueCommand.class, this::stringCommands)
                .matchAny(o -> log().warning("received unknown message: {}", o)).build());
    }

    void process(final CardActor.Retrieve cc) {
        log().info("Received message: {}", cc);
        final Card card = cards.getOrDefault(cc.card.getNumber(), null);
        log().info("found: {}", card);
        sender().tell(new CommandProcessed(Optional.ofNullable(card),cc), self());
    }

    void process(final CardActor.Create cc) {
        log().debug("Received message: {}", cc);
        cards.put(cc.card.getNumber(), cc.getCard());
        sender().tell(new CommandProcessed(Optional.of(cc.getCard()),cc), self());
    }

     void process(final ReverseImage cri) {
        log().debug("Reverse card image: {}", cri);

        final List<Integer> bytes = cri.getCard().getImage();
        Collections.reverse(bytes);

        final Card card = new Card(cri.getCard().getNumber(), cri.getCard().getDescription(), bytes);
        log().debug("result image: {}", card);

        sender().tell(new CommandProcessed(Optional.of(card),cri), self());
    }

    void stringCommands(final ValueCommand command) {
        if (Reset.equals(command)) {
            reset();
        }
        sender().tell(new CommandProcessed(Optional.empty(),command), self());
    }

    void reset() {
        cards.clear();
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class Retrieve extends Command {
        Card card;
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class Create extends Command {
        Card card;
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class ReverseImage extends Command {
        Card card;
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static final class CommandProcessed extends Event {
        Optional<Card> card;
        Command command;
    }
}
