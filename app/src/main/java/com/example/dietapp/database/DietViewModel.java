package com.example.dietapp.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class DietViewModel extends AndroidViewModel {
    private DietRepository repository;
    List<Diet> diets;

    DietViewModel(Application application){
        super(application);
        repository = new DietRepository(application);
        diets = repository.getAllDiets();
    }
    List<Diet> getAllDiets(){
        return diets;
    }
    void insert(Diet diets){
        repository.insert(diets);
    }
}
