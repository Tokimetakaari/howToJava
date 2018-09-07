package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class BooleanField
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int entryId;
    private int fieldPosition;
    private int value;
    // 0 = false
    // 1 = true

    public BooleanField()
    {

    }

    public BooleanField(int position, int newValue)
    {
        fieldPosition = position;
        value= newValue;
    }


    @NonNull
    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(@NonNull int entryId) {
        this.entryId = entryId;
    }

    public int getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(int fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
