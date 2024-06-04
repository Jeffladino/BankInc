package com.credibanco.app.credibanco_app.entities;


import java.time.LocalDateTime;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "card")
public class Card {

    @Id
    private Long cardId;

    @Size(min=10, max=20)
    private String nombreTitular;
    
    @Min(0)
    private Integer balance;
   
    
    private LocalDateTime fechaVencimiento;

   
    private LocalDateTime fechaCreacion;

    private char activa;


    


    public Card() {
        this.balance = 0;
        this.activa = 'B';
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long carId) {
        this.cardId = carId;
    }
    public String getNombreTitular() {
        return nombreTitular;
    }
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public Integer getBalance() {
        return balance;
    }
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    public char getActiva() {
        return activa;
    }
    public void setActiva(char activa) {
        this.activa = activa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    



}
