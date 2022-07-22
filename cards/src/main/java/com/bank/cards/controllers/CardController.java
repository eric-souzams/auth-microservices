package com.bank.cards.controllers;

import com.bank.cards.models.Card;
import com.bank.cards.models.Properties;
import com.bank.cards.services.CardService;
import com.bank.cards.services.impl.CardsServiceConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardsServiceConfig cardsServiceConfig;

    @GetMapping("/{id}")
    public ResponseEntity<List<Card>> getCardDetails(@PathVariable("id") Long customerId) {
        List<Card> cards = cardService.findByCustomerId(customerId);

        return ResponseEntity.ok(cards);
    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion(),
                cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());

        return ow.writeValueAsString(properties);
    }
}
