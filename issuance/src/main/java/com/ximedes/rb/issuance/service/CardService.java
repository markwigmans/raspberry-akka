package com.ximedes.rb.issuance.service;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.ximedes.rb.issuance.model.Card;
import com.ximedes.rb.issuance.service.actor.CardActor;
import com.ximedes.rb.issuance.service.actor.ActorManager;
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

    public Observable<CardActor.CommandProcessed> create(final Card card) {
        log.debug("create({})", card);
        final CardActor.Create command = CardActor.Create.builder().card(card).build();
        return ObservableUtil.from(Patterns.ask(cardActor, command, timeout), ec);
    }
}
