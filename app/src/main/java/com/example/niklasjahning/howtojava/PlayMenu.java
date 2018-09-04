package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class PlayMenu extends AppCompatActivity implements View.OnClickListener , BooleanDao {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30;
    Intent i ;
    MyDatabaseAdapter database;
    int numOfButtons = 30;
    static int positionOfNewLevel = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_menu);
        setupButtons();
        test();
        setupListener();
        setupDataBase();
        setupDataEntries();
        unlockLevel();
    }

    private void setupDataBase()
    {
        //database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "appDataBase").build();
        database = new MyDatabaseAdapter(this);
        database.open();
    }

    private void setupDataEntries()
    {
        for (int j = 0; j < numOfButtons; j++)
        {
            insertBooleanField(new BooleanField(j, false));
            database.notify();
            if (j == 0)
            {
                insertBooleanField(new BooleanField(0, true));
                database.notify();
            }
        }
    }

    private void setupButtons()
    {
        b1 = findViewById(R.id.play_lektion_1);
        b2 = findViewById(R.id.play_lektion_2);
        b2.setVisibility(View.INVISIBLE);
        b2.setClickable(false);
        b3 = findViewById(R.id.play_lektion_3);
        b3.setVisibility(View.INVISIBLE);
        b3.setClickable(false);
        b4 = findViewById(R.id.play_lektion_4);
        b5 = findViewById(R.id.play_lektion_5);
        b6 = findViewById(R.id.play_lektion_6);
        b7 = findViewById(R.id.play_lektion_7);
        b8 = findViewById(R.id.play_lektion_8);
        b9 = findViewById(R.id.play_lektion_9);
        b10 = findViewById(R.id.play_lektion_10);
        b11 = findViewById(R.id.play_lektion_11);
        b12 = findViewById(R.id.play_lektion_12);
        b13 = findViewById(R.id.play_lektion_13);
        b14 = findViewById(R.id.play_lektion_14);
        b15 = findViewById(R.id.play_lektion_15);
        b16 = findViewById(R.id.play_lektion_16);
        b17 = findViewById(R.id.play_lektion_17);
        b18 = findViewById(R.id.play_lektion_18);
        b19 = findViewById(R.id.play_lektion_19);
        b20 = findViewById(R.id.play_lektion_20);
        b21 = findViewById(R.id.play_lektion_21);
        b22 = findViewById(R.id.play_lektion_22);
        b23 = findViewById(R.id.play_lektion_23);
        b24 = findViewById(R.id.play_lektion_24);
        b25 = findViewById(R.id.play_lektion_25);
        b26 = findViewById(R.id.play_lektion_26);
        b27 = findViewById(R.id.play_lektion_27);
        b28 = findViewById(R.id.play_lektion_28);
        b29 = findViewById(R.id.play_lektion_29);
        b30 = findViewById(R.id.play_lektion_30);


    }

    private void setupListener()
    {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);
        b17.setOnClickListener(this);
        b18.setOnClickListener(this);
        b19.setOnClickListener(this);
        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);
        b24.setOnClickListener(this);
        b25.setOnClickListener(this);
        b26.setOnClickListener(this);
        b27.setOnClickListener(this);
        b28.setOnClickListener(this);
        b29.setOnClickListener(this);
        b30.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.play_lektion_1: i = new Intent(PlayMenu.this, ExerciseSelectDatatypes.class);
                break;
            case R.id.play_lektion_2: i = new Intent(PlayMenu.this, ExerciseSelectDatatypes2.class);
               break;
            case R.id.play_lektion_3: i = new Intent(PlayMenu.this, ExerciseOperators.class);
                break;
            case R.id.play_lektion_4: i = new Intent(PlayMenu.this, ExerciseOperators.class);
                break;
            case R.id.play_lektion_5: i = new Intent(PlayMenu.this, ExerciseSelectDatatypes.class);
                break;
            case R.id.play_lektion_6: i = new Intent(PlayMenu.this, ExerciseOperators.class);
                break;
            case R.id.play_lektion_7: i = new Intent(PlayMenu.this, ExerciseSelectDatatypes.class);
                break;
            case R.id.play_lektion_8: i = new Intent(PlayMenu.this, ExerciseOperators.class);
                break;
        }
        startActivity(i);
    }


    @Override
    public void insertBooleanField(BooleanField booleanField) {

    }

    @Override
    public List<BooleanField> getBooleans(int search) {
        return null;
    }

    private void test ()
    {
        for (int k = 0 ; k < numOfButtons; k++)
        {
            BooleanField booleanOne = (BooleanField) getBooleans(k);
            switch (k)
            {
                case 1: if ( booleanOne.getValue() )
                        {
                            b2.setClickable(true);
                            b2.setVisibility(View.VISIBLE);
                        }
                        break;
                case 2: if ( booleanOne.getValue() )
                {
                    b3.setClickable(true);
                    b3.setVisibility(View.VISIBLE);
                }
                    break;

            }
        }

    }

    private void unlockLevel()
    {
        BooleanField booleanOne = (BooleanField) getBooleans(positionOfNewLevel);
        booleanOne.setValue(true);
        database.notify();
    }
}
