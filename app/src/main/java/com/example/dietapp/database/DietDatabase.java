package com.example.dietapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Diet.class}, version = 1)
public abstract class DietDatabase extends RoomDatabase {
    public abstract DietDAO dietDAO();

}
