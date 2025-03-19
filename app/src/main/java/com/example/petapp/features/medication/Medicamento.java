package com.example.petapp.features.medication;

import java.io.Serializable;

public class Medicamento implements Serializable {
    private int id;
    private String nombreMascota;
    private String nombreMedicamento;
    private String dosis;
    private String frecuencia;

    public Medicamento(String nombreMascota, String nombreMedicamento, String dosis, String frecuencia) {
        this.nombreMascota = nombreMascota;
        this.nombreMedicamento = nombreMedicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

    // Constructor para editar (con ID)
    public Medicamento(int id, String nombreMascota, String nombreMedicamento, String dosis, String frecuencia) {
        this.id = id;
        this.nombreMascota = nombreMascota;
        this.nombreMedicamento = nombreMedicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombreMascota() {
        return nombreMascota;
    }
    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }
    public String getNombreMedicamento() {
        return nombreMedicamento;
    }
    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }
    public String getDosis() {
        return dosis;
    }
    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
    public String getFrecuencia() {
        return frecuencia;
    }
    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
}
