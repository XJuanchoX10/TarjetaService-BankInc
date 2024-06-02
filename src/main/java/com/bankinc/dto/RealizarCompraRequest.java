package com.bankinc.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public class RealizarCompraRequest {

    @Schema(description = "Identificador de la tarjeta", example = "1020301234567801")
    private String cardId;

    @Schema(description = "Monto de la compra", example = "100")
    private double price;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
