package com.bankinc.service;

import com.bankinc.dto.RealizarCompraRequest;
import com.bankinc.entity.Transaccion;

public interface TransaccionService {
    Transaccion realizarCompra(RealizarCompraRequest request);
    Transaccion consultarTransaccion(Long id);
    Transaccion anularTransaccion(String numeroTarjeta, Long id);
}
