package com.example.dietapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// data access object
@Dao
public interface UserDao {
    @Insert
    void setInsertUser(User user);

    @Update
    void setUpdateUser(User user);

    @Delete
    void setDeleteUser(User user);

    // 조회 쿼리
    @Query("SELECT * FROM User") // query : select라고 생각
    List<User> getUserAll();


}
