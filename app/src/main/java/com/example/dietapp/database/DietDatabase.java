package com.example.dietapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Diet.class}, version = 1)
public abstract class DietDatabase extends RoomDatabase {
    public abstract DietDAO dietDAO();
    public static final int NumberOfThread = 4;

    private static volatile DietDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor
             = Executors.newFixedThreadPool(NumberOfThread);

    public static DietDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DietDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DietDatabase.class, "DietApp.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
