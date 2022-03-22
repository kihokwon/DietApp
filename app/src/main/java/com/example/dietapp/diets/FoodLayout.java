package com.example.dietapp.diets;

public class FoodLayout {
    private String foodName;
    private int kcal;
    private int protein;
    private int carbo;
    private int fat;
    private int glyco;

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getKcal() {
        return kcal;
    }

    public String getKcalString() {return Integer.toString(kcal);}

    public String getFoodName() {
        return foodName;
    }

    public void setCarbo(int carbo) {
        this.carbo = carbo;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setGlyco(int glyco) {
        this.glyco = glyco;
    }

    public int getCarbo() {
        return carbo;
    }

    public int getFat() {
        return fat;
    }

    public int getGlyco() {
        return glyco;
    }

    public int getProtein() {
        return protein;
    }
}

