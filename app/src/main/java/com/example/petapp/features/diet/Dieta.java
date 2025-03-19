package com.example.petapp.features.diet;


import java.io.Serializable;

public class Dieta implements Serializable {
    private int id;
    private String tipoComida;
    private double cantidad;
    private String hora;

    public Dieta(String tipoComida, double cantidad, String hora) {
        this.tipoComida = tipoComida;
        this.cantidad = cantidad;
        this.hora = hora;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipoComida() {
        return tipoComida;
    }
    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
}
