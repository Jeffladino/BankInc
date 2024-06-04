package com.credibanco.app.credibanco_app.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.credibanco.app.credibanco_app.entities.Card;

import jakarta.transaction.Transactional;

public interface ICardRepository extends CrudRepository<Card, Long> {



    @Transactional
    @Modifying
    @Query("UPDATE Card c SET c.balance = :balance WHERE c.cardId = :idCard")
    void updateBalanceById(Long idCard, Integer balance);



}
