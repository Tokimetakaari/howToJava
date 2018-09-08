package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface BooleanDao
{
    @Insert
    void insertBooleanField (BooleanField booleanField);

    @Query("SELECT * FROM BooleanField WHERE entryId LIKE :search")
    BooleanField getBooleans(int search);




}
