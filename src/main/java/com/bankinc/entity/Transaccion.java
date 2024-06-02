package com.bankinc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id", nullable = false)
    private Tarjeta tarjeta;

    @Column(nullable = false)
    private double monto;

    @Column(nullable = false)
    private LocalDateTime fechaTransaccion;

    @Column(nullable = false)
    private boolean anulada;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }
}
