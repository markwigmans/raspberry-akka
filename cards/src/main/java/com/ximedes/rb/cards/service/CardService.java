package com.ximedes.rb.cards.service;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.ximedes.rb.cards.model.Card;
import com.ximedes.rb.cards.service.actor.CardActor;
import com.ximedes.rb.cards.service.actor.ActorManager;
import com.ximedes.rb.model.Command;
import com.ximedes.rb.service.ObservableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;
import scala.concurrent.ExecutionContext;


/**
 * Created by mawi on 31/10/2015.
 */
@Service
@Slf4j
public class CardService {
    private final ActorRef cardActor;
    private final ExecutionContext ec;
    private final Timeout timeout;

    /**
     * Auto wired constructor
     */
    @Autowired
    public CardService(final ActorManager actorManager, final Timeout timeout) {
        this.cardActor = actorManager.getCardActor();
        this.ec = actorManager.getEc();
        this.timeout = timeout;
    }

    public Observable<CardActor.CommandProcessed> reset() {
        log.debug("reset()");
        final Command command = CardActor.Reset;
        return ObservableUtil.from(Patterns.ask(cardActor, command, timeout), ec);
    }

    public Observable<CardActor.CommandProcessed> retrieve(final Card card) {
        log.debug("retrieve({})", card);
        final Command command = new CardActor.Retrieve(card);
        return ObservableUtil.from(Patterns.ask(cardActor, command, timeout), ec);
    }

    public Observable<CardActor.CommandProcessed> create(final Card card) {
        log.debug("create({})", card);
        final Command command = new CardActor.Create(card);
        return ObservableUtil.from(Patterns.ask(cardActor, command, timeout), ec);
    }

    public Observable<CardActor.CommandProcessed> reverseImage(final Card card) {
        log.debug("reverse image({})", card);
        final Command command = new CardActor.ReverseImage(card);
        return ObservableUtil.from(Patterns.ask(cardActor, command, timeout), ec);
    }
}
