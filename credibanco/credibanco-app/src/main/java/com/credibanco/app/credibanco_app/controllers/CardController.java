package com.credibanco.app.credibanco_app.controllers;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.app.credibanco_app.entities.Card;
import com.credibanco.app.credibanco_app.services.ICardService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    ICardService iCardService;

    

    @GetMapping("/{productId}")
    public ResponseEntity<?> view(@PathVariable Long productId) {

        Optional<Card> cardOptional = iCardService.findById(productId);
        if(cardOptional.isPresent()){
            return ResponseEntity.ok(cardOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}/number")
    public ResponseEntity<Card> save( @PathVariable Long productId,  @RequestBody Card card) {


        if((productId + generarId()).length() == 16 && !card.getNombreTitular().isEmpty()){
            Long id = Long.parseLong( productId + generarId() );
            LocalDateTime  today = LocalDateTime.now();
    
            card.setCardId(id);
            card.setFechaCreacion(today);
            card.setFechaVencimiento(card.getFechaCreacion().plusYears(3));
    
            Card cardNew = iCardService.save(card);
    
            return ResponseEntity.status(HttpStatus.CREATED).body(cardNew);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

      
    }

    
    @PostMapping("/enroll")
    public ResponseEntity<?> activarTarjeta( @RequestBody  Card card ) {

        Optional<Card> cardOptional = iCardService.updateCard(card.getCardId());
        if(cardOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(cardOptional.orElseThrow());

        }

        return ResponseEntity.notFound().build();


    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> eliminarTarjeta( @PathVariable Long cardId){

        Optional<Card> cardOptional = iCardService.blockCard(cardId);

        if(cardOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(cardOptional.orElseThrow());

        }

        return ResponseEntity.notFound().build();



    }


    
     @PostMapping("/balance")
    public ResponseEntity<?> recargarSaldo(@Valid @RequestBody  Card card ) {
       
        Optional<Card> cardOptional = iCardService.addBalance(card.getCardId(), card.getBalance());
        if(cardOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body( cardOptional.orElseThrow());

        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<?> consultaSaldo(@PathVariable Long cardId) {

        Optional<Card> cardOptional = iCardService.findById(cardId);
        if(cardOptional.isPresent()){
            return ResponseEntity.ok( cardOptional.orElseThrow().getBalance());
        }

        return ResponseEntity.notFound().build();
    }
    



    private String generarId( ){

         // Se define el rango 
        long min = 1000000000L;
        long max = 9999999999L;

        // Generar un número aleatorio en el rango [min, max]
        long randomNumber = ThreadLocalRandom.current().nextLong(min, max + 1);
       
        return String.valueOf(randomNumber);
    }
    

}
