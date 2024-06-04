package com.credibanco.app.credibanco_app.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.credibanco.app.credibanco_app.entities.enums.TransaccionEstado;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaccionId;

    private Integer price;

    @CreationTimestamp
    private LocalDateTime fechaTransaccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card cardEntity;

    @Enumerated(value = EnumType.STRING)
    private TransaccionEstado transaccionEstado;

    public Long getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(Long transaccionId) {
        this.transaccionId = transaccionId;
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

 

    public TransaccionEstado getTransaccionEstado() {
        return transaccionEstado;
    }

    public void setTransaccionEstado(TransaccionEstado transaccionEstado) {
        this.transaccionEstado = transaccionEstado;
    }

    public Card getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(Card cardEntity) {
        this.cardEntity = cardEntity;
    }

    

    


    




}
