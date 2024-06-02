package com.bankinc.dao;

import com.bankinc.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    Optional<Tarjeta> findByNumeroTarjeta(String numeroTarjeta);
}