package com.example.dietapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dietapp.database.User;
import com.example.dietapp.database.UserDao;

@Database(entities = {User.class }, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
