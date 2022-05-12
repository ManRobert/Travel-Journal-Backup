package com.example.traveljournal.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "trip_table_test6")
public class Trip {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String destination;
    @NotNull
    private String type;
    @NotNull
    private int price;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;
    @NotNull
    private float rate;
    @NotNull
    private String image;
    @NotNull
    private int favourite;

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    public Trip(@NotNull String name, @NotNull String destination, @NotNull String type, int price, @NotNull String startDate, @NotNull String endDate, float rate, @NotNull String image) {
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
        this.image = image;
        this.favourite = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getDestination() {
        return destination;
    }

    public void setDestination(@NotNull String destination) {
        this.destination = destination;
    }

    @NotNull
    public String getType() {
        return type;
    }

    public void setType(@NotNull String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NotNull
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull String startDate) {
        this.startDate = startDate;
    }

    @NotNull
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull String endDate) {
        this.endDate = endDate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @NotNull
    public String getImage() {
        return image;
    }

    public void setImage(@NotNull String image) {
        this.image = image;
    }


}
