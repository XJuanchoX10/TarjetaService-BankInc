package com.bankinc.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroTarjeta;

    @Column(nullable = false)
    private String nombreTitular;

    @Column(nullable = false)
    private String apellidoTitular;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @Column(nullable = false)
    private boolean activa;

    @Column(nullable = false)
    private boolean bloqueada;

    @Column(nullable = false)
    private double saldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getApellidoTitular() {
        return apellidoTitular;
    }

    public void setApellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
