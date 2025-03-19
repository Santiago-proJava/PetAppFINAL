package com.example.petapp.features.registerPet;


import android.os.Parcel;
import android.os.Parcelable;

public class Pet implements Parcelable {
    private String name;
    private String species;
    private String breed;
    private int age;
    private double weight;

    public Pet(String name, String species, String breed, int age, double weight) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    // --- PARCELABLE IMPLEMENTATION ---
    protected Pet(Parcel in) {
        name = in.readString();
        species = in.readString();
        breed = in.readString();
        age = in.readInt();
        weight = in.readDouble();
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(species);
        dest.writeString(breed);
        dest.writeInt(age);
        dest.writeDouble(weight);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
