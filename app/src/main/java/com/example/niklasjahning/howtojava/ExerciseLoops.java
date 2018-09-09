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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseLoops extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mySound;
    TextView viewOne, viewTwo, viewThree;
    EditText textOne, textTwo,textThree;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers=0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    private NotificationHelper nHelper;
    private Intent next;
    boolean[] answerCorrect = new boolean[5];
    boolean[] answered = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_exercise);
        setupItems();
        setupDrawer();
        connectBurger();
        mySound = MediaPlayer.create(this,R.raw.sound);
        setText();
    }

    private void setupItems() {
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
        submit = findViewById(R.id.programmingExerciseButton);
        nHelper = new NotificationHelper(this);
        submit.setOnClickListener(this);
        next = new Intent(this,ExerciseKonstruktoren.class);
    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseLoops.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseLoops.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseLoops.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseLoops.this, DataTypes.class);
                        startActivity(intent);
                        break;
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),R.string.credits_text,Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.checkbox_submit_button)
        {
            mySound.start();
            update();
            sendNotification(getString(R.string.notifyTitle8), getString(R.string.notifyMessage) ,next);
            finish();
        }
    }


    private void update()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayMenu.unlockLevelNumber=8;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
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
        String inputSolution;
        inputSolution = textOne.getText().toString();
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
                if (input == ' ' || input == '\n') {
                    a++;
                } else {
                    if (solutions == ' '||  solutions == '\n')
                    {
                        b++;
                    }
                    else{
                        return false;
                    }
                }
            }

        }return true;
    }
    private void compareTwoStrings()
    {
        switch (i) {
            case 0:
                String correctSolution = "public int addieren(int a, int b) { int c = a + b ;return c;}";
                String correctSolutionTwo  = "public int addieren(int a, int b){ int c; c = a + b ;return c;}";
                String correctSolutionThree = "public int addieren(int a, int b) {return a+b;}";

                if (compareStrings(correctSolution) || compareStrings(correctSolutionTwo) || compareStrings(correctSolutionThree)) {
                    viewOne.setText(R.string.programming_exercises_answer_correct);
                    i++;
                    setText();
                } else {
                    viewOne.setText(R.string.programming_exercises_answer_not_correct);
                }
                break;
            case 1:
                correctSolution           = "public int addieren(int a, int b) { while (a < b){ a = a+1;} return a;}";
                correctSolutionTwo        = "public int addieren(int a, int b) { while (a < b){ a ++;}return a;}";
                correctSolutionThree       = "public int addieren(int a, int b) { while (a < b){ a += 1;}return a;}";

                if (compareStrings(correctSolution) || compareStrings(correctSolutionTwo)  || compareStrings(correctSolutionThree)) {
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

    public void sendNotification(String title, String message, Intent next) {
        NotificationCompat.Builder nBuilder = nHelper.getChannelNotification(title, message, next);
        nHelper.getNotificationManager().notify(1, nBuilder.build());
    }

}


