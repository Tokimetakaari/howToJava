package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class StorageEntry
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int entryId;
    private String confiqName;
    private int value;
    // 0 = false
    // 1 = true

    public StorageEntry()
    {

    }




    @NonNull
    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(@NonNull int entryId) {
        this.entryId = entryId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getConfiqName() {
        return confiqName;
    }

    public void setConfiqName(String confiqName) {
        this.confiqName = confiqName;
    }
}
