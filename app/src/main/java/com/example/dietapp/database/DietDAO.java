package com.example.dietapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.io.Serializable;
import java.util.List;

// Data Access Object
@Dao
public interface DietDAO  {
    // prevent to db unique key error
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setInsertDiet(Diet diet);

    @Update
    void setUpdateDiet(Diet diet);

    @Delete
    void setDeleteDiet(Diet diet);

    @Query("SELECT * FROM Diet")
    List<Diet> getUserAll();

//    @Query("SELECT * FROM Diet WHERE foodName = 유저가 검색한 단어 ) // user가 검색한 단어를 어떤식으로 연결시킬지를 생각해야함.

//    @Query("SELECT foodName FROM Diet")
//    List<Diet> getFoodName();
}

