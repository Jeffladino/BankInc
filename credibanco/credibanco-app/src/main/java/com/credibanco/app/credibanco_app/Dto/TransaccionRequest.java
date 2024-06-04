package com.credibanco.app.credibanco_app.Dto;

public class TransaccionRequest {


    private Integer price;
    private Long cardId;
    private Long transaccionId;

    
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
    public Long getTransaccionId() {
        return transaccionId;
    }
    public void setTransaccionId(Long transaccionId) {
        this.transaccionId = transaccionId;
    }

    

}
