package com.ximedes.rb.cards;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Created by mawi on 26/05/2016.
 */
@SpringApplicationConfiguration(classes = CardApp.class)
@WebIntegrationTest(randomPort = true)
@TestPropertySource("classpath:test.properties")
public abstract class IntegrationTest {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }
}
