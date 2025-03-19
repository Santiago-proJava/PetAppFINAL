package com.example.petapp.features.weight;


public class WeightRecord {
    private long id;
    private double weight;
    private String date;

    public WeightRecord(long id, double weight, String date) {
        this.id = id;
        this.weight = weight;
        this.date = date;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
