package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BooleanDao
{
    @Insert(onConflict = REPLACE)
    void insertBooleanField (BooleanField booleanField);

    @Query("SELECT * FROM BooleanField")
    List<BooleanField> getBooleans();

}
