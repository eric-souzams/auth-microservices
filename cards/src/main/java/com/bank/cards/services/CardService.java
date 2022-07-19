package com.bank.cards.services;

import com.bank.cards.models.Card;

import java.util.List;

public interface CardService {

    List<Card> findByCustomerId(Long customerId);

}
