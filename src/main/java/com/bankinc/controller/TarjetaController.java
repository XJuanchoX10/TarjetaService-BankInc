package com.bankinc.controller;

import com.bankinc.dto.ActivarTarjetaRequest;
import com.bankinc.dto.RecargarSaldoRequest;
import com.bankinc.entity.Tarjeta;
import com.bankinc.exception.InvalidRequestException;
import com.bankinc.exception.ResourceNotFoundException;
import com.bankinc.exception.TarjetaNotFoundException;
import com.bankinc.service.TarjetaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/card")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    private static final Pattern PRODUCT_ID_PATTERN = Pattern.compile("^\\d{1,10}$");

    @GetMapping("/{productId}/number")
    @Operation(summary = "Generar número de tarjeta", description = "Generar número de tarjeta.")
    public Tarjeta generarNumeroDeTarjeta(@PathVariable String productId) {
        if (!PRODUCT_ID_PATTERN.matcher(productId).matches()) {
            throw new InvalidRequestException("El ID de producto es inválido. Debe ser un número de hasta 10 dígitos.");
        }

        try {
            return tarjetaService.generarNumeroDeTarjeta(productId);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el número de tarjeta", e);
        }
    }

    @PostMapping("/enroll")
    @Operation(summary = "Activar Tarjeta", description = "Activar una tarjeta.")
    public Tarjeta activarTarjeta(@RequestBody ActivarTarjetaRequest request) {
        try {
            String cardId = request.getCardId();
            if (cardId == null || cardId.isEmpty()) {
                throw new InvalidRequestException("El ID de la tarjeta es requerido");
            }
            return tarjetaService.activarTarjeta(cardId);
        } catch (TarjetaNotFoundException e) {
            throw new InvalidRequestException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error al activar la tarjeta", e);
        }
    }

    @DeleteMapping("/{cardId}")
    @Operation(summary = "Bloquear tarjeta", description = "Bloquear tarjeta.")
    public void bloquearTarjeta(@PathVariable String cardId) {
        try {
            tarjetaService.bloquearTarjeta(cardId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al bloquear la tarjeta");
        }
    }

    @PostMapping("/balance")
    @Operation(summary = "Recargar Saldo", description = "Recargar saldo a una tarjeta.")
    public Tarjeta recargarSaldo(@RequestBody RecargarSaldoRequest request) {
        try {
            String cardId = request.getCardId();
            double balance = request.getBalance();
            return tarjetaService.recargarSaldo(cardId, balance);
        } catch (Exception e) {
            throw new RuntimeException("Error al recargar saldo", e);
        }
    }

    @GetMapping("/balance/{cardId}")
    @Operation(summary = "Consulta de saldo", description = "Consulta de saldo.")
    public double consultarSaldo(@PathVariable String cardId) {
        try {
            return tarjetaService.consultarSaldo(cardId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al consultar saldo");
        }
    }
}