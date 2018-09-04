package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database( entities = BooleanField.class, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract BooleanDao booleanDao();
}
