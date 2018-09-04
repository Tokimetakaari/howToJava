package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BooleanField
{
    @PrimaryKey
    private int fieldPosition;
    private boolean value;

    public BooleanField(int position, boolean passed)
    {
        this.fieldPosition = position;
        this.value = passed;
    }


    public void setValue(boolean newValue)
    {
        value = newValue;
    }
    public int getFieldPosition()
    {
        return fieldPosition;
    }
    public boolean getValue()
    {
        return value;
    }
}
