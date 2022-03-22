package com.example.dietapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diet {
    // autoGenerate = true 고유 id value
    @PrimaryKey(autoGenerate = false)
    // NO
    private int no;
    // 상용제품
    private String subject;
    // 식품명
    private String foodName;
    // 연도
    private int year;
    // 지역
    private String region;
    // 1회 제공량
    private int volume;
    // 내용량 단위
    private String unit;
    // 에너지
    private int kcal;
    // 단백질
    private int protein;
    // 지방
    private int fat;
    // 탄수화물
    private int cal;
    // 총당류
    private int sugar;
    // 나트륨
    private int na;
    // 콜레스테롤
    private int chol;
    // 포화지방
    private int SFat;
    // 성분표출처
    private String source;
    // 발행기관
    private String issuer;

    public Diet() {

    }

    public Diet(int no, String foodName, int kcal, int protein, int fat, int cal){
        this.no = no;
        this.foodName = foodName;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.cal = cal;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getNa() {
        return na;
    }

    public void setNa(int na) {
        this.na = na;
    }

    public int getChol() {
        return chol;
    }

    public void setChol(int chol) {
        this.chol = chol;
    }

    public int getSFat() {
        return SFat;
    }

    public void setSFat(int SFat) {
        this.SFat = SFat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
