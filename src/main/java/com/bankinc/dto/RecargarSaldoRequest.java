package com.bankinc.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RecargarSaldoRequest {

    @Schema(description = "Identificador de la tarjeta", example = "1020301234567801")
    private String cardId;

    @Schema(description = "Saldo a recargar", example = "10000")
    private double balance;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
