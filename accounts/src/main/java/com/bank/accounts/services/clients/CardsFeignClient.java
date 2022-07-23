package com.bank.accounts.services.clients;

import com.bank.accounts.models.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/cards/{id}")
    List<Card> getCardDetails(@PathVariable("id") Long customerId);

}
