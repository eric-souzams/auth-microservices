package com.bank.cards.services.impl;

import com.bank.cards.models.Card;
import com.bank.cards.repositories.CardRepository;
import com.bank.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> findByCustomerId(Long customerId) {
        return cardRepository.findByCustomerId(customerId);
    }
}
