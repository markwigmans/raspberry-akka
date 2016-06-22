package com.ximedes.rb.cards.api;

import com.google.common.primitives.Ints;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.ximedes.rb.cards.CardApp;
import com.ximedes.rb.cards.IntegrationTest;
import com.ximedes.rb.cards.api.model.ApiCard;
import com.ximedes.rb.cards.model.Card;
import com.ximedes.rb.cards.service.CardService;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;


/**
 * Created by mawi on 12/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CardRestControllerTest extends IntegrationTest {

    @Autowired
    private CardService cardService;

    @Before
    public void setUp() {
        super.setUp();
        cardService.reset();
    }

    @Test
    public void canCreateCard() {
        final ApiCard card = ApiCard.builder().number("123").build();
        RequestSpecification spec = new RequestSpecBuilder().setAccept(ContentType.JSON).setContentType(ContentType.JSON).build();

        given().spec(spec).body(card)
                .when().post("/card")
                .then().statusCode(HttpStatus.SC_OK).body("number", equalTo(card.getNumber()));
    }

    @Test
    public void canRetrieveCard() {
        final String cardNumber = "123";
        final Card card = new Card(cardNumber, Optional.empty(), Collections.emptyList());
        cardService.create(card);

        RequestSpecification spec = new RequestSpecBuilder().setAccept(ContentType.JSON).setContentType(ContentType.JSON).build();

        final Object result = given().spec(spec)
                .when().get("/card/" + cardNumber)
                .then().statusCode(HttpStatus.SC_OK).extract().body().path("number");
        assertTrue(result.equals(cardNumber));
    }

    @Test
    public void retrieveNonExistingCard() {
        final RequestSpecification spec = new RequestSpecBuilder().setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON).build();

        given().spec(spec)
                .when().get("/card/123")
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }


    @Test
    public void reserveCardImage() {
        final int[] image = IntStream.rangeClosed(1, 100).toArray();
        final int[] reverse = IntStream.rangeClosed(image.length, image[0]).toArray();

        ApiCard card = ApiCard.builder().number("123").image(image).build();
        RequestSpecification spec = new RequestSpecBuilder().setAccept(ContentType.JSON).setContentType(ContentType.JSON).build();

        final List<Integer> body = given().spec(spec).body(card)
                .when().post("/card/image/reverse")
                .then().statusCode(HttpStatus.SC_OK).extract().body().path("image");

        // check
        assertTrue(body.stream().collect(Collectors.toList()).containsAll(Ints.asList(reverse)));
    }

}