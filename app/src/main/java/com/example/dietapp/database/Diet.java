package com.example.dietapp.database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diet {
    @PrimaryKey(autoGenerate = true)
    private int id = 0; // table 고유값 // 자동으로 키값인 id가 자동으로 생성
    private String product; // 품목대표는 첫줄에 나타나게끔 하기 위해서 필요할 듯.
    private String foodName;
    private int years;
    private String region;
    private String foodDiv1;
    private String foodDiv2;
    private int servingSize;
    private String servingUnit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

