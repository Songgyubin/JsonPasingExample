package com.sizey.cardata;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "car")
public class Car{

    @PrimaryKey
    private int id;
    private String carModel1;
    private String carModel2;
    private int year;
    private boolean isFavorite = false;


    public Car(int id, String carModel1, String carModel2, int year, boolean isFavorite) {
        this.id = id;
        this.carModel1 = carModel1;
        this.carModel2 = carModel2;
        this.year = year;
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getId() {
        return id;
    }

    public String getCarModel1() {
        return carModel1;
    }

    public String getCarModel2() {
        return carModel2;
    }

    public int getYear() {
        return year;
    }
}
