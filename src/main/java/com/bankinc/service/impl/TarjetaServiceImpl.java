package com.bankinc.service.impl;

import com.bankinc.dao.TarjetaRepository;
import com.bankinc.entity.Tarjeta;
import com.bankinc.exception.TarjetaNotFoundException;
import com.bankinc.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public Tarjeta generarNumeroDeTarjeta(String productId) {
        String numeroTarjeta = generarNumeroDeTarjetaString(productId);
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNumeroTarjeta(numeroTarjeta);
        tarjeta.setActiva(false);
        tarjeta.setSaldo(0.0);
        //Inicializar otros campos si es necesario
        tarjeta.setNombreTitular("Default");
        tarjeta.setApellidoTitular("Default");
        tarjeta.setFechaVencimiento(LocalDate.now().plusYears(5));
        tarjeta.setBloqueada(false);

        return tarjetaRepository.save(tarjeta);
    }

    private String generarNumeroDeTarjetaString(String productId) {
        Random random = new Random();
        StringBuilder numeroTarjeta = new StringBuilder(productId);
        for (int i = 0; i < 10; i++) {
            numeroTarjeta.append(random.nextInt(10));
        }
        return numeroTarjeta.toString();
    }

    @Override
    public Tarjeta activarTarjeta(String numeroTarjeta) {
        Tarjeta tarjeta = tarjetaRepository.findByNumeroTarjeta(numeroTarjeta)
                .orElseThrow(() -> new TarjetaNotFoundException("El número de tarjeta es incorrecto o no existe."));
        tarjeta.setActiva(true);
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public void bloquearTarjeta(String numeroTarjeta) {
        Optional<Tarjeta> optionalTarjeta = tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
        if (optionalTarjeta.isPresent()) {
            Tarjeta tarjeta = optionalTarjeta.get();
            tarjeta.setBloqueada(true);
            tarjetaRepository.save(tarjeta);
        } else {
            throw new RuntimeException("Tarjeta no encontrada");
        }
    }

    @Override
    public Tarjeta recargarSaldo(String numeroTarjeta, double monto) {
        Optional<Tarjeta> optionalTarjeta = tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
        if (optionalTarjeta.isPresent()) {
            Tarjeta tarjeta = optionalTarjeta.get();
            tarjeta.setSaldo(tarjeta.getSaldo() + monto);
            tarjetaRepository.save(tarjeta);
            return tarjeta;
        } else {
            throw new RuntimeException("Tarjeta no encontrada");
        }
    }

    @Override
    public double consultarSaldo(String numeroTarjeta) {
        Optional<Tarjeta> optionalTarjeta = tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
        return optionalTarjeta.map(Tarjeta::getSaldo).orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
    }
}
