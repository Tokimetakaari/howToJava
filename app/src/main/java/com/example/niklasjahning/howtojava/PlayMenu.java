package com.example.niklasjahning.howtojava;

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

public class PlayMenu extends AppCompatActivity implements View.OnClickListener {

    Intent i ;
    private  int numOfButtons = 18;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    static int unlockLevelNumber ;
    Button[] buttonList = new Button[numOfButtons];





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(SettingsMenu.switchOnOff1) {
            setTheme(R.style.Kai);
        }
        setContentView(R.layout.play_menu);
        setupButtons();
        getValues();
        unlockLevel();
        setupDrawer();
        setupListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getValues();
        unlockLevel();
    }


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
        buttonList[0]=findViewById(R.id.play_lektion_1);
        buttonList[1]=findViewById(R.id.play_lektion_2);
        buttonList[2]=findViewById(R.id.play_lektion_3);
        buttonList[3]=findViewById(R.id.play_lektion_4);
        buttonList[4]=findViewById(R.id.play_lektion_5);
        buttonList[5]=findViewById(R.id.play_lektion_6);
        buttonList[6]=findViewById(R.id.play_lektion_7);
        buttonList[7]=findViewById(R.id.play_lektion_8);
        buttonList[8]=findViewById(R.id.play_lektion_9);
        buttonList[9]=findViewById(R.id.play_lektion_10);
        buttonList[10]=findViewById(R.id.play_lektion_11);
        buttonList[11]=findViewById(R.id.play_lektion_12);
        buttonList[12]=findViewById(R.id.play_lektion_13);
        buttonList[13]=findViewById(R.id.play_lektion_14);
        buttonList[14]=findViewById(R.id.play_lektion_15);
        buttonList[15]=findViewById(R.id.play_lektion_16);
        buttonList[16]=findViewById(R.id.play_lektion_17);
        buttonList[17]=findViewById(R.id.play_lektion_18);

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
                    case R.id.moveToTheory:
                        Toast.makeText(getApplicationContext(),"Du bist aktuell in keiner Übung",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),"Thanks for playing!",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void setupListener()
    {
        for (int i = 0; i < numOfButtons; i++)
        {
            buttonList[i].setOnClickListener(this);
        }
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
            case R.id.play_lektion_3: i = new Intent(PlayMenu.this, ExerciseClassesAndObjects.class);
                break;
            case R.id.play_lektion_4: i = new Intent(PlayMenu.this, ExerciseClassesAndObjects2.class);
                break;
            case R.id.play_lektion_5: i = new Intent(PlayMenu.this, ExerciseOperators.class);
                break;
            case R.id.play_lektion_6: i = new Intent(PlayMenu.this, ExerciseIfElse.class);
                break;
            case R.id.play_lektion_7: i = new Intent(PlayMenu.this,ExerciseArray.class);
                break;
            case R.id.play_lektion_8: i = new Intent(PlayMenu.this, ExerciseOperators.class);
                break;
            case R.id.play_lektion_9: i = new Intent(PlayMenu.this, ExerciseOperators.class);
                break;
            case R.id.play_lektion_10: i = new Intent(PlayMenu.this, ExerciseKonstruktoren.class);
                break;
            case R.id.play_lektion_11: i = new Intent(PlayMenu.this, ExerciseForMethods1.class);
                break;
            case R.id.play_lektion_12: i = new Intent(PlayMenu.this, ProgrammingExerciseForMethods.class);
                break;
            case R.id.play_lektion_13: i = new Intent(PlayMenu.this, ExerciseVererbung.class);
                break;
            case R.id.play_lektion_14: i = new Intent(PlayMenu.this, ExerciseInterfaces.class);
                break;
            case R.id.play_lektion_15: i = new Intent(PlayMenu.this, ExerciseEvents.class);
                break;
            case R.id.play_lektion_16: i = new Intent(PlayMenu.this, ExerciseTryCatchPhrase.class);
                break;
            case R.id.play_lektion_17: i = new Intent(PlayMenu.this, ExerciseExeptions.class);
                break;
            case R.id.play_lektion_18: i = new Intent(PlayMenu.this, ExerciseBufferedReader.class);
                break;
                default:
                    break;

        }
        startActivity(i);
    }

    private void unlockLevel()
    {

        for ( int i = 0; i< numOfButtons; i++) {

            buttonList[i].setEnabled(unlockLevelNumber >= i);
        }
    }

    private void getValues()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StorageEntry searchEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                if (searchEntry != null)
                {
                    unlockLevelNumber = (searchEntry.getValue());

                }
            }
        }).start();
    }
}


