package com.credibanco.app.credibanco_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.app.credibanco_app.entities.Card;
import com.credibanco.app.credibanco_app.repositories.ICardRepository;

@Service
public class ImplCardService  implements ICardService{

    @Autowired
    private ICardRepository iCardRepository;

    private static final char activo = 'A';
    private static final char bloquear = 'B';

    @Transactional(readOnly = true)
    @Override
    public Optional<Card> findById(Long id) {
       
        return iCardRepository.findById(id);

    }

    @Transactional
    @Override
    public Card save(Card card) {
        
        return iCardRepository.save(card);
    }

    @Transactional
    @Override
    public Optional<Card>  updateCard(Long id) {

         Optional<Card> card =  activarOBloquearTarjeta(id, activo);

         return card;
        
     

    }

    @Transactional
    @Override
    public Optional<Card> blockCard(Long id) {

        Optional<Card> card =  activarOBloquearTarjeta(id, bloquear);

        return card;
    }


    @Override
    @Transactional
    public Optional<Card> addBalance(Long id, Integer balance) {
        

        Integer total = 0;

        Optional<Card> cardOptional = iCardRepository.findById(id);


        if(cardOptional.isPresent()){

            Card cardB = cardOptional.orElseThrow();
            total += cardB.getBalance() + balance;
           
            cardB.setBalance(total);
            return Optional.of( iCardRepository.save(cardB));

        }
        return cardOptional;

    }

    @Override
    @Transactional
    public Optional<Card> decreaseBalance(Long id, Integer balance) {
        Integer total = 0;

        Optional<Card> cardOptional = iCardRepository.findById(id);


        if(cardOptional.isPresent()){

            Card cardB = cardOptional.orElseThrow();
            total -= cardB.getBalance() + balance;
           
            cardB.setBalance(total);
            return Optional.of(iCardRepository.save(cardB));

        }
        return cardOptional;
    }



    //Función para bloquear o desbloquear tarjeta

    private Optional<Card> activarOBloquearTarjeta(Long id, char activar){

        Optional<Card> cardOptional = iCardRepository.findById(id);

        if(cardOptional.isPresent()){
            Card cardB = cardOptional.orElseThrow();
            cardB.setActiva(activar);
            return Optional.of(iCardRepository.save(cardB));

        }
        return cardOptional;

    }

  

 



    

}
