package com.bankinc.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public class AnularTransaccionRequest {

    @Schema(description = "Identificador de la tarjeta", example = "1020301234567801")
    private String cardId;

    @Schema(description = "Identificador de la transacción", example = "123456")
    private Long transactionId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
