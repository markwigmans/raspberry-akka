package com.ximedes.rb.cards.api;

import com.ximedes.rb.cards.api.model.ApiCard;
import com.ximedes.rb.cards.api.model.mapping.DomainMapper;
import com.ximedes.rb.cards.model.Card;
import com.ximedes.rb.cards.service.CardService;
import com.ximedes.rb.cards.service.actor.CardActor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by mawi on 27/10/2015.
 */
@RestController
@RequestMapping(value = "/card")
@Slf4j
public class CardRestController {

    private final CardService cardService;
    private final DomainMapper domainMapper;

    /**
     * Auto wired constructor
     */
    @Autowired
    public CardRestController(final CardService cardService, final DomainMapper domainMapper) {
        this.cardService = cardService;
        this.domainMapper = domainMapper;
    }

    @RequestMapping(value = "/{cardNumber}", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<ApiCard>> retrieve(@PathVariable String cardNumber) {
        log.info("retrieve({})", cardNumber);
        val deferredResult = new DeferredResult<ResponseEntity<ApiCard>>();

        val card = new Card(cardNumber, Optional.empty(), Collections.emptyList());
        val future = cardService.retrieve(card);
        future.map(r -> domainMapper.map(r.getCard().orElseThrow(() -> new IllegalArgumentException()), ApiCard.class))
                .subscribe(c -> deferredResult.setResult(new ResponseEntity(c, HttpStatus.OK)),
                        (error -> deferredResult.setResult(new ResponseEntity(null, HttpStatus.NOT_FOUND))));
        return deferredResult;
    }

    /**
     * @param apiCard
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public DeferredResult<ApiCard> create(@RequestBody ApiCard apiCard) {
        log.info("create({})", apiCard);
        final DeferredResult<ApiCard> deferredResult = new DeferredResult<>();

        final Card card = domainMapper.map(apiCard, Card.class);
        final Observable<CardActor.CommandProcessed> future = cardService.create(card);
        future.map(r -> domainMapper.map(r.getCard().get(), ApiCard.class))
                .subscribe(c -> deferredResult.setResult(c), deferredResult::setErrorResult);

        return deferredResult;
    }


    /**
     * @param apiCard
     * @return
     */
    @RequestMapping(value = "/image/reverse", method = RequestMethod.POST)
    public DeferredResult<ApiCard> reserveImage(@RequestBody ApiCard apiCard) {
        log.info("reserve image({})", apiCard);
        final DeferredResult<ApiCard> deferredResult = new DeferredResult<>();

        final Card card = domainMapper.map(apiCard, Card.class);
        final Observable<CardActor.CommandProcessed> future = cardService.reverseImage(card);
        future.map(r -> domainMapper.map(r.getCard().get(), ApiCard.class))
                .subscribe(c -> deferredResult.setResult(c), deferredResult::setErrorResult);

        return deferredResult;
    }
}
