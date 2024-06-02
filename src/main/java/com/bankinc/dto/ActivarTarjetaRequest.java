package com.bankinc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ActivarTarjetaRequest {

    @Schema(description = "Identificador de la tarjeta", example = "1020301234567801")
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}