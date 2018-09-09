package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProgrammingExerciseForMethods extends AppCompatActivity implements View.OnClickListener {


    MediaPlayer mySound;
    Button submit;
    TextView viewOne, viewTwo, viewThree;
    EditText textOne, textTwo,textThree;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    int i = 0;
    private NotificationHelper nHelper;
    private Intent next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_exercise);
        setupDrawer();
        connectBurger();
        setupItems();
        mySound = MediaPlayer.create(this,R.raw.sound);
        setText();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.programmingExerciseButton)
        {
            if ( i < 2)
            {compareTwoStrings();}
            else
            {
                if ( i == 2)
                {
                    mySound.start();
                    sendNotification(getString(R.string.notifyTitle11), getString(R.string.notifyMessage), next);
                    update();
                }

                finish();
            }


        }
    }
    private void update()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayMenu.unlockLevelNumber=11;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void setupItems()
    {

        textOne = findViewById(R.id.programmingExerciseEditText_1);
        textTwo = findViewById(R.id.programmingExerciseEditText_2);
        textTwo.setVisibility(View.GONE);
        textThree = findViewById(R.id.programmingExerciseEditText_3);
        textThree.setVisibility(View.GONE);
        viewOne = findViewById(R.id.programmingExerciseTextView_1);
        viewTwo = findViewById(R.id.programmingExerciseTextView_2);
        viewTwo.setVisibility(View.GONE);
        viewThree = findViewById(R.id.programmingExerciseTextView_3);
        viewThree.setVisibility(View.GONE);
        submit = findViewById(R.id.programmingExerciseButton);
        submit.setOnClickListener(this);
        nHelper = new NotificationHelper(this);
        next = new Intent(this,ExerciseSelectDatatypes2.class);
    }

    private void setText()
    {
        switch (i)
        {
            case 0: viewOne.setText(R.string.programming_methods_exercise1);
                    break;
            case 1: viewOne.setText(R.string.programming_methods_exercise2);
                    break;
        }
    }

    private boolean compareStrings(String solution)
    {
        String inputSolution = textOne.getText().toString();
        int a = 0;
        int b = 0;
        char solutions;
        char input;
        for( int i =0; i < inputSolution.length(); i++) {
            solutions =  solution.charAt(b);
            input = inputSolution.charAt(a);
            if (solutions==input)
            {
                a++;
                b++;
            }
            else {
                if (input == ' ')  {
                    a++;
                } else {
                    if (input == '\n')
                    {a++;}
                    else {
                        if (solutions == ' ')
                        {
                            b++;
                        }
                        else{
                            if (  solutions == '\n')
                            {b++;}
                            else
                            {
                                return false;
                            }
                        }
                    }
                }
            }

        }return true;
    }
    private void compareTwoStrings()
    {
        switch (i) {
            case 0:
                if (compareStrings(getString(R.string.exerciseProgrammingMethodsA1))
                        || compareStrings( getString(R.string.exerciseProgrammingMethodsA1A1))
                        || compareStrings(getString(R.string.exerciseProgrammingMethodsA1A2))) {
                    viewOne.setText(R.string.programming_exercises_answer_correct);
                    i++;
                    setText();
                } else {
                    viewOne.setText(R.string.programming_exercises_answer_not_correct);
                }
                break;
            case 1:
                if (compareStrings(getString(R.string.exerciseProgrammingMethodsA2A1))
                        || compareStrings(getString(R.string.exerciseProgrammingMethodsA2A2))
                        || compareStrings(getString(R.string.exerciseProgrammingMethodsA2A3))) {
                    viewOne.setText(R.string.programming_exercises_answer_correct);
                    i++;
                    setText();
                } else {
                    viewOne.setText(R.string.programming_exercises_answer_not_correct);
                }
                break;
            default: i++;
                break;
        }
    }


        private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ProgrammingExerciseForMethods.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ProgrammingExerciseForMethods.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ProgrammingExerciseForMethods.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ProgrammingExerciseForMethods.this, DataTypes.class);
                        startActivity(intent);
                        break;
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),getString(R.string.credits_text),Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.burgerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendNotification(String title, String message, Intent next) {
        NotificationCompat.Builder nBuilder = nHelper.getChannelNotification(title, message, next);
        nHelper.getNotificationManager().notify(1, nBuilder.build());
    }
}
