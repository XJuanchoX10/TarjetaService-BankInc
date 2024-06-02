package com.bankinc.service.impl;

import com.bankinc.dao.TarjetaRepository;
import com.bankinc.dao.TransaccionRepository;
import com.bankinc.dto.RealizarCompraRequest;
import com.bankinc.entity.Tarjeta;
import com.bankinc.entity.Transaccion;
import com.bankinc.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    @Transactional
    public Transaccion realizarCompra(RealizarCompraRequest request) {
        // Buscar la tarjeta por numeroTarjeta
        Tarjeta tarjeta = tarjetaRepository.findByNumeroTarjeta(request.getCardId())
                .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

        // Verificar saldo suficiente
        if (tarjeta.getSaldo() < request.getPrice()) {
            throw new RuntimeException("Saldo insuficiente para realizar la compra");
        }

        // Actualizar saldo de la tarjeta
        tarjeta.setSaldo(tarjeta.getSaldo() - request.getPrice());

        // Guardar la tarjeta actualizada
        tarjetaRepository.save(tarjeta);

        // Crear y guardar la transacción
        Transaccion transaccion = new Transaccion();
        transaccion.setTarjeta(tarjeta);
        transaccion.setMonto(request.getPrice());
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setAnulada(false);
        transaccionRepository.save(transaccion);

        return transaccion;
    }

    @Override
    public Transaccion consultarTransaccion(Long id) {
        return transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
    }

    @Override
    @Transactional
    public Transaccion anularTransaccion(String numeroTarjeta, Long id) {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
        if (!transaccion.isAnulada() && transaccion.getFechaTransaccion().isAfter(LocalDateTime.now().minusHours(24))) {
            transaccion.setAnulada(true);
            transaccionRepository.save(transaccion);

            Tarjeta tarjeta = tarjetaRepository.findByNumeroTarjeta(numeroTarjeta)
                    .orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
            tarjeta.setSaldo(tarjeta.getSaldo() + transaccion.getMonto());
            tarjetaRepository.save(tarjeta);
        }
        return transaccion;
    }
}
