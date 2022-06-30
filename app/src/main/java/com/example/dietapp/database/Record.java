package com.example.dietapp.database;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;
import java.util.List;

public class Record {
    private Date date;
    private String breakfasts;
    private String lunch;
    private String dinner;
    private String snacks;

    private int kcal;
    private int protein;
    private int fat;
    private int carbo;

    private String result;

    public Record(Date date, String breakfasts, String lunch, String dinner, String snacks, int kcal, int carbo, int protein, int fat, String result) {
        this.date = date;
        this.breakfasts = breakfasts;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snacks = snacks;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carbo = carbo;
        this.result = result;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public void setBreakfasts(String breakfasts) {
        this.breakfasts = breakfasts;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setCal(int carbo) {
        this.carbo = carbo;
    }


    public int getKcal() {
        return kcal;
    }

    public Date getDate() {
        return date;
    }

    public String getBreakfasts() {
        return breakfasts;
    }

    public String getDinner() {
        return dinner;
    }

    public String getLunch() {
        return lunch;
    }

    public int getProtein() {
        return protein;
    }

    public String getSnacks() {
        return snacks;
    }

    public int getFat() {
        return fat;
    }

    public int getCal() {
        return carbo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
