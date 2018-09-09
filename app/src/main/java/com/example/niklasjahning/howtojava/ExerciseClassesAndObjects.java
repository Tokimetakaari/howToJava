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

public class ExerciseClassesAndObjects extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mySound;
    TextView textView;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers = 0;
    EditText answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8;
    private NotificationHelper nHelper;
    private Intent next;
    boolean[] answerCorrect = new boolean[4];
    boolean[] answered = new boolean[4];
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloze_default);
        setupDrawer();
        connectBurger();
        setupItems();
        mySound = MediaPlayer.create(this,R.raw.sound);
        setText();


    }

    private void setupItems() {
        textView = findViewById(R.id.cloze_text);
        answer1 = findViewById(R.id.cloze_answer_1);
        answer2 = findViewById(R.id.cloze_answer_2);
        answer3 = findViewById(R.id.cloze_answer_3);
        answer4 = findViewById(R.id.cloze_answer_4);
        answer5 = findViewById(R.id.cloze_answer_5);
        answer6 = findViewById(R.id.cloze_answer_6);
        answer7 = findViewById(R.id.cloze_answer_7);
        answer8 = findViewById(R.id.cloze_answer_8);
        submit = findViewById(R.id.cloze_submit_button);
        nHelper = new NotificationHelper(this);
        submit.setOnClickListener(this);
        next = new Intent(this,ExerciseClassesAndObjects2.class);
    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseClassesAndObjects.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseClassesAndObjects.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseClassesAndObjects.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseClassesAndObjects.this, ClassesAndObjects.class);
                        startActivity(intent);
                        break;
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),"Thanks for playing!",Toast.LENGTH_SHORT).show();
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
    public void onClick(View view) {
        if(view.getId() == R.id.cloze_submit_button) {
            if ((!answer1.getText().toString().isEmpty()) && (i <=4)) {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();

            } else if (i > 4) {
                if (numOfCorrectAnswers >=  answered.length /2) {
                    mySound.start();
                    sendNotification(getString(R.string.notifyTitle3), getString(R.string.notifyMessage), next);
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
                PlayMenu.unlockLevelNumber=3;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText(R.string.exerciseClassesAndObjectsQ1);
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
                answer5.setVisibility(View.GONE);
                answer6.setVisibility(View.GONE);
                answer7.setVisibility(View.GONE);
                answer8.setVisibility(View.GONE);
                break;
            case 1: textView.setText(R.string.exerciseClassesAndObjectsQ2);
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
                answer5.setVisibility(View.GONE);
                answer6.setVisibility(View.GONE);
                answer7.setVisibility(View.GONE);
                answer8.setVisibility(View.GONE);
                break;
            case 2:
                textView.setText(R.string.exerciseClassesAndObjectsQ3);
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
                answer5.setVisibility(View.GONE);
                answer6.setVisibility(View.GONE);
                answer7.setVisibility(View.GONE);
                answer8.setVisibility(View.GONE);
                break;
            case 3:
                textView.setText(R.string.exerciseClassesAndObjectsQ4);
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
                answer5.setVisibility(View.GONE);
                answer6.setVisibility(View.GONE);
                answer7.setVisibility(View.GONE);
                answer8.setVisibility(View.GONE);
                break;
            case 4:
                for (int j = 0; j < 4; j++) {
                    if (answerCorrect[j]) {
                        numOfCorrectAnswers ++;
                    }
                }
                textView.setText(getString(R.string.endScreenExercise,numOfCorrectAnswers,answered.length));
                answer1.setVisibility(View.INVISIBLE);
                answer1.setClickable(false);
                submit.setText(R.string.endScreenSubmit);
                i++;
                break;
        }
        resetAnswers();
    }


    private void resetAnswers() {
        answer1.setText("");
    }


    private void checkCorrectAnswers() {
        switch (i) {

            case 0:
                if (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA1))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA1A1)))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA1A2)))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA1A3)))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA1A4)))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA1A5)))) {
                    answerCorrect[i] = true;
                }
                break;
            case 1:
                if (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA2))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA2A1)))) {
                    answerCorrect[i] = true;
                }
                break;
            case 2:
                if (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA3))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA3A1)))) {
                    answerCorrect[i] = true;
                }
                break;
            case 3:
                if (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA4))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA4A1)))
                        || (answer1.getText().toString().equals(getString(R.string.exerciseClassesAndObjectsA4A2)))
                        || (answer1.getText().toString().equals(R.string.exerciseClassesAndObjectsA4A3))
                        || (answer1.getText().toString().equals(R.string.exerciseClassesAndObjectsA4A4))
                        || (answer1.getText().toString().equals(R.string.exerciseClassesAndObjectsA4A5))
                        || (answer1.getText().toString().equals(R.string.exerciseClassesAndObjectsA4A6))
                        || (answer1.getText().toString().equals(R.string.exerciseClassesAndObjectsA4A7))) {
                    answerCorrect[i] = true;
                }
                break;


        }
    }


    public void sendNotification(String title, String message, Intent next) {
        NotificationCompat.Builder nBuilder = nHelper.getChannelNotification(title, message, next);
        nHelper.getNotificationManager().notify(1, nBuilder.build());
    }


}
