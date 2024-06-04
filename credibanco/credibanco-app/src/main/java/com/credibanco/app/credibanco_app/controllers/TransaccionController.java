package com.credibanco.app.credibanco_app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.app.credibanco_app.Dto.TransaccionRequest;
import com.credibanco.app.credibanco_app.entities.Card;
import com.credibanco.app.credibanco_app.entities.Transaccion;
import com.credibanco.app.credibanco_app.entities.enums.TransaccionEstado;
import com.credibanco.app.credibanco_app.services.ICardService;
import com.credibanco.app.credibanco_app.services.ITransaccionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/transaction")
public class TransaccionController {

    @Autowired
    ITransaccionService iTransaccionService;

    @Autowired
    ICardService iCardService;

    @PostMapping("/purchase")
    public ResponseEntity<?> TransaccionCompra(@RequestBody TransaccionRequest transaccionRequest){

        Transaccion transNew = new Transaccion();
        Optional<Card> cardOptional = iCardService.findById(transaccionRequest.getCardId());
        if(cardOptional.isPresent() && transaccionRequest.getPrice()>=0  ){
            transNew.setPrice(transaccionRequest.getPrice());
            return ResponseEntity.status(HttpStatus.CREATED).body(iTransaccionService.save(transNew, transaccionRequest.getCardId()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el envio de la peticion, validar la tarjeta o el precio no sea menor  a  0");
           

    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?>  consultarTransaccion(@PathVariable Long transactionId ) {

     
            return ResponseEntity.ok(iTransaccionService.findById(transactionId));
    
        
    }

    @PostMapping("/anulation")
    public ResponseEntity<?> anularTransaccion(@RequestBody TransaccionRequest transaccionRequest){

        Optional<Card> cardOptional = iCardService.findById(transaccionRequest.getCardId());
        if(cardOptional.isPresent()   ){
            return ResponseEntity.ok(
                iTransaccionService.anulTransaccionDto(transaccionRequest.getCardId(), transaccionRequest.getTransaccionId(), TransaccionEstado.ANULADA));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al anular la transacción validar la tarjeta.");

    

    }

    

}
