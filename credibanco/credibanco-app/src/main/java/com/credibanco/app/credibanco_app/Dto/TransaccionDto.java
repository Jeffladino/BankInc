package com.credibanco.app.credibanco_app.Dto;

import java.time.LocalDateTime;

import com.credibanco.app.credibanco_app.entities.enums.TransaccionEstado;

public class TransaccionDto {

    private Long transaccionId;
    public TransaccionEstado getTransaccionEstado() {
        return transaccionEstado;
    }
    public void setTransaccionEstado(TransaccionEstado transaccionEstado) {
        this.transaccionEstado = transaccionEstado;
    }
    private Integer price;
    private LocalDateTime fechaTransaccion;
    private Long cardId;
    private TransaccionEstado transaccionEstado;


    public Long getTransaccionId() {
        return transaccionId;
    }
    public void setTransaccionId(Long id) {
        this.transaccionId = id;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }
    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    

}
