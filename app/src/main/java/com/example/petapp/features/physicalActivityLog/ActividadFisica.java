package com.example.petapp.features.physicalActivityLog;

public class ActividadFisica {
    private int id;
    private String tipo;
    private String duracion;
    private String fecha;

    public ActividadFisica(int id, String tipo, String duracion, String fecha) {
        this.id = id;
        this.tipo = tipo;
        this.duracion = duracion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
