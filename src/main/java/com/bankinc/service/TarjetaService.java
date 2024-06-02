package com.bankinc.service;

import com.bankinc.entity.Tarjeta;

public interface TarjetaService {
    Tarjeta generarNumeroDeTarjeta(String productId);
    Tarjeta activarTarjeta(String numeroTarjeta);
    void bloquearTarjeta(String numeroTarjeta);
    Tarjeta recargarSaldo(String numeroTarjeta, double monto);
    double consultarSaldo(String numeroTarjeta);
}
