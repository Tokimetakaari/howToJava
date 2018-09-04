package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class PlayMenu extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30;
    Intent i ;
    MyDatabaseAdapter database;
    AppDatabase appDatabase;
    int numOfButtons = 30;
    static int positionOfNewLevel = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_menu);
        setupButtons();
        setupDrawer();
//        test();
        setupListener();
//        setupDataBase();
//        setupDataEntries();
//        unlockLevel();
    }

 /*   private void setupDataBase()
    {
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "appDataBase").build();
        database = new MyDatabaseAdapter(this);
        database.open();
    }

    private void setupDataEntries()
    {
        for (int j = 0; j < numOfButtons; j++)
        {
            database.insertMyObject(new BooleanField(0,false));
            //insertBooleanField(new BooleanField(j, false));
            database.notify();
            if (j == 0)
            {
                database.insertMyObject(new BooleanField(0,true));
                //insertBooleanField(new BooleanField(0, true));
                database.notify();
            }
        }
    } */
    private void setupDrawer() {
     mDrawerLayout = (DrawerLayout) findViewById(R.id.burgerLayout);
     mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
     mDrawerLayout.addDrawerListener(mToggle);
     mToggle.syncState();
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }




        return super.onOptionsItemSelected(item);
    }

    private void setupButtons()
    {
        b1 = findViewById(R.id.play_lektion_1);
        b2 = findViewById(R.id.play_lektion_2);
        b3 = findViewById(R.id.play_lektion_3);
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
        connectBurger();


    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:

                        Toast.makeText(getApplicationContext(),"Du befindest dich bereits in Play",Toast.LENGTH_SHORT).show();

                    case R.id.theory_menu:
                        i = new Intent(PlayMenu.this, TheoryMenu.class);
                        startActivity(i);
                        break;
                    case R.id.setting_menu:
                        i = new Intent(PlayMenu.this, SettingsMenu.class);
                        startActivity(i);
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),"Thanks for playing!",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
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
            case R.id.play_lektion_4: i = new Intent(PlayMenu.this, ExerciseClassesAndObjects.class);
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
    /*

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
    } */
}
