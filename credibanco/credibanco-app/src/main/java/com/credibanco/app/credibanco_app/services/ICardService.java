package com.credibanco.app.credibanco_app.services;

import java.util.Optional;

import com.credibanco.app.credibanco_app.entities.Card;

public interface ICardService {

    Optional<Card> findById(Long id);
    Card save(Card card);
    Optional<Card> updateCard(Long id);
    Optional<Card> blockCard(Long id);
    Optional<Card> addBalance(Long id, Integer balance);
    Optional<Card> decreaseBalance (Long id, Integer balance);





}
