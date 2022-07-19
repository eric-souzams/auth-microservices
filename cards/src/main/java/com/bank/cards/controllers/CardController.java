package com.bank.cards.controllers;

import com.bank.cards.models.Card;
import com.bank.cards.services.CardService;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<Card>> getCardDetails(@PathVariable("id") Long customerId) {
        List<Card> cards = cardService.findByCustomerId(customerId);

        return ResponseEntity.ok(cards);
    }
}
