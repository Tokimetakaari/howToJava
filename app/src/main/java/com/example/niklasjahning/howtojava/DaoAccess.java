package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface DaoAccess
{
    @Insert
    void insertEntry (StorageEntry storageEntry);

    @Query("SELECT * FROM StorageEntry WHERE confiqName LIKE :confiqName")
    StorageEntry getConfiqEntry (String confiqName);

    @Update
    void updateEntries(StorageEntry entry);



}
