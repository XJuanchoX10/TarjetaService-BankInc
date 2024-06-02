package com.bankinc.controller;

import com.bankinc.dto.AnularTransaccionRequest;
import com.bankinc.dto.RealizarCompraRequest;
import com.bankinc.entity.Transaccion;
import com.bankinc.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bankinc.exception.ResourceNotFoundException;

import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/purchase")
    @Operation(summary = "Transacción de compra", description = "Transacción de compra.")
    public Transaccion realizarCompra(@RequestBody RealizarCompraRequest request) {
        try {
            return transaccionService.realizarCompra(request);
        } catch (Exception e) {
            throw new RuntimeException("Error al realizar la compra", e);
        }
    }


    @GetMapping("/{transactionId}")
    @Operation(summary = "Consultar transacción", description = "Consultar transacción.")
    public Transaccion consultarTransaccion(@PathVariable Long transactionId) {
        try {
            return transaccionService.consultarTransaccion(transactionId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al consultar la transacción");
        }
    }

    @PostMapping("/anulation")
    @Operation(summary = "Anular transacción", description = "Anular transacción.")
    public Transaccion anularTransaccion(@RequestBody AnularTransaccionRequest request) {
        try {
            String cardId = request.getCardId();
            Long transactionId = request.getTransactionId();
            return transaccionService.anularTransaccion(cardId, transactionId);
        } catch (Exception e) {
            throw new RuntimeException("Error al anular la transacción", e);
        }
    }
}
