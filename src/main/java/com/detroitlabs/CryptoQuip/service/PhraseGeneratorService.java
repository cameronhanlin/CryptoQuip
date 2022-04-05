package com.detroitlabs.CryptoQuip.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PhraseGeneratorService {
    public String[] getPhrase() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://ron-swanson-quotes.herokuapp.com/v2/quotes", String[].class);
    }



}
