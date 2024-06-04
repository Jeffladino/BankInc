package com.credibanco.app.credibanco_app.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.app.credibanco_app.Dto.TransaccionDto;
import com.credibanco.app.credibanco_app.entities.Card;
import com.credibanco.app.credibanco_app.entities.Transaccion;
import com.credibanco.app.credibanco_app.entities.enums.TransaccionEstado;
import com.credibanco.app.credibanco_app.repositories.ICardRepository;
import com.credibanco.app.credibanco_app.repositories.ITransaccionRepository;

@Service
public class ImplTransaccionService implements ITransaccionService {

     @Autowired
     private ITransaccionRepository transaccionRepository;

     @Autowired 
     private ICardRepository iCardRepository;


    @Transactional(readOnly = true)
    @Override
    public TransaccionDto findById(Long id) {

        Transaccion transaccion = transaccionRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Transaccion Id invalido"));
        
        
        return conveTransaccionDto(transaccion);
        
    }

    @Transactional
    @Override
    public Transaccion save(Transaccion transaccion, Long cardId) {


        Integer total = 0;

        Optional<Card> cardOptional = iCardRepository.findById(cardId);

        if(cardOptional.isPresent()){

            Card cardB = cardOptional.orElseThrow();

            //Validación de la tarjeta y que el saldo no sea mayor al precio
            //En caso de cumplir el condicional se almacena la transacción pero queda anulada

            if(cardB.getActiva()=='B' ||   cardB.getBalance()< transaccion.getPrice()){
                transaccion.setTransaccionEstado(transaccion.getTransaccionEstado().ANULADA);
                transaccion.setCardEntity(cardB);
                return transaccionRepository.save(transaccion);
            } else{
                
                total = cardB.getBalance() - transaccion.getPrice();

                cardB.setBalance(total);
    
                transaccion.setCardEntity(cardB);
    
               
                transaccion.setTransaccionEstado(transaccion.getTransaccionEstado().CONFIRMADA);
    
                iCardRepository.updateBalanceById(cardId, total);

                return transaccionRepository.save(transaccion);
            }
           
         

        }else{
            transaccion.setTransaccionEstado(transaccion.getTransaccionEstado().ANULADA);
            return transaccionRepository.save(transaccion);
        }
        
       
    }


    
    @Override
    public TransaccionDto anulTransaccionDto(Long cardId, Long idTransaccion, TransaccionEstado transaccionEstado) {

        Integer total = 0;

        Transaccion transaccion = transaccionRepository.findById(idTransaccion).orElseThrow(
            () -> new IllegalArgumentException("Transaccion Id invalido"));
            

            if(esMenorDe24Horas(transaccion.getFechaTransaccion()) && !transaccion.getTransaccionEstado().equals(transaccionEstado.ANULADA)){

                Optional<Card> cardOptional = iCardRepository.findById(cardId);

                if(cardOptional.isPresent()){
                transaccionRepository.updateEstadoById(cardId, idTransaccion, transaccionEstado.ANULADA);

        
                    Card cardB = cardOptional.orElseThrow();
                    total = cardB.getBalance() + transaccion.getPrice();

                    cardB.setBalance(total);
                    transaccion.setTransaccionEstado(transaccionEstado);

                    iCardRepository.updateBalanceById(cardId, total);
                    

                }

            }
        
            

        
        return conveTransaccionDto(transaccion);
    }


    private boolean esMenorDe24Horas(LocalDateTime fechaTransaccion) {
        LocalDateTime fechaActual = LocalDateTime.now();
        Duration duracion = Duration.between(fechaTransaccion, fechaActual);
        return Math.abs(duracion.toHours()) <= 24;
    }

    //Funcion que recibe un objeto transacción y lo convierte a un objeto DTO

    private TransaccionDto conveTransaccionDto(Transaccion transaccion){
        TransaccionDto transaccionDto = new TransaccionDto();
        transaccionDto.setTransaccionId(transaccion.getTransaccionId());
        transaccionDto.setPrice(transaccion.getPrice());
        transaccionDto.setFechaTransaccion(transaccion.getFechaTransaccion());
        transaccionDto.setCardId(transaccion.getCardEntity().getCardId());
        transaccionDto.setTransaccionEstado(transaccion.getTransaccionEstado());
        return transaccionDto;
    }







}
