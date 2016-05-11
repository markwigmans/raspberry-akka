package com.ximedes.rb.portal.service;

import com.ximedes.rb.portal.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mawi on 23/12/2015.
 */
@Service
@Slf4j
public class IssuanceService {

    @Value("${issuance.url:http://issuance-service}")
    private String serviceUrl;

    @LoadBalanced
    @Autowired
    private RestTemplate restTemplate;

    public Card addCard(final Card card) {
        Card result = restTemplate.postForObject(serviceUrl + "/issuance/card", card, Card.class);
        return result;
    }
}
