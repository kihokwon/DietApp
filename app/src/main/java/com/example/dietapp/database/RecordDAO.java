package com.example.dietapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void setInsertRecord (Record record);

    @Update
    void setUpdateRecord(Record record);

    @Delete
    void setDeleteRecord(Record record);

    @Query("SELECT * FROM Record")
    List<Record> getUserAll();
}
