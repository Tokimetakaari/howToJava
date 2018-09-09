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

public class ExerciseSelectDatatypes2 extends AppCompatActivity implements View.OnClickListener {


    TextView textView;
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers = 0;
    MediaPlayer mySound;
    private NotificationHelper nHelper;
    boolean[] answerCorrect = new boolean[3];
    boolean[] answered = new boolean[3];
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    private Intent next;


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

    private void setText() {

        switch (i) {
            case 0:
                textView.setText(R.string.exerciseSelectDatatypes2Q1);
                editText2.setVisibility(View.GONE);
                editText3.setVisibility(View.GONE);
                editText4.setVisibility(View.GONE);
                editText5.setVisibility(View.GONE);
                editText6.setVisibility(View.GONE);
                editText7.setVisibility(View.GONE);
                editText8.setVisibility(View.GONE);
                break;
            case 1:
                textView.setText(R.string.exerciseSelectDatatypes2Q2);
                break;
            case 2:
                textView.setText(R.string.exerciseSelectDatatypes2Q3);
                break;

            case 3:
                countCorrectAnswers();
                textView.setText(getString(R.string.endScreenExercise, numOfCorrectAnswers, answered.length));
                editText1.setVisibility(View.INVISIBLE);
                editText1.setClickable(false);
                submit.setText(R.string.endScreenSubmit);
                i++;
                break;


        }
        resetEdit();
    }

    private void countCorrectAnswers() {
        for (int j = 0; j < 3; j++)
        {
            if (answerCorrect[j])
            {numOfCorrectAnswers ++;}
        }
    }

    private void setupItems() {

        textView = findViewById(R.id.cloze_text);
        editText1 = findViewById(R.id.cloze_answer_1);
        submit = findViewById(R.id.cloze_submit_button);
        submit.setOnClickListener(this);
        editText2 = findViewById(R.id.cloze_answer_2);
        editText3 = findViewById(R.id.cloze_answer_3);
        editText4 = findViewById(R.id.cloze_answer_4);
        editText5 = findViewById(R.id.cloze_answer_5);
        editText6 = findViewById(R.id.cloze_answer_6);
        editText7 = findViewById(R.id.cloze_answer_7);
        editText8 = findViewById(R.id.cloze_answer_8);
        nHelper = new NotificationHelper(this);
        next = new Intent(this,ExerciseClassesAndObjects.class);



    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseSelectDatatypes2.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseSelectDatatypes2.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseSelectDatatypes2.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseSelectDatatypes2.this, DataTypes.class);
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
        if (view.getId() == R.id.cloze_submit_button) {
            if ((!editText1.getText().toString().isEmpty()) && (i <= 3)) {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();

            } else if (i > 3) {
                if (numOfCorrectAnswers >=  answered.length /2) {
                    mySound.start();
                    sendNotification(getString(R.string.notifyTitle1), getString(R.string.notifyMessage),next);
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
                PlayMenu.unlockLevelNumber=2;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void checkCorrectAnswers() {

//        Falls mehrere Schreibweise zählen einfach eine weitere Bedingung mit || hinzufügen editText1.getText().toString().equals(alternative1)

        switch (i) {
            case 0:
                if (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A1))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A1A1)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A1A2)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A1A3)))) {
                    answerCorrect[i] = true;
                }
            case 1:
                if (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A2))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A2A1)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A2A2)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A2A3)))) {
                    answerCorrect[i] = true;
                }
                break;
            case 2:
                if (editText1.getText().toString().equals(getString(R.string.exerciseSelectDatatypes2A3))) {
                    answerCorrect[i] = true;

                }


        }

    }

    private void resetEdit()
    {
        editText1.setText("");
    }


    public void sendNotification(String title, String message, Intent next) {
        NotificationCompat.Builder nBuilder = nHelper.getChannelNotification(title, message, next);
        nHelper.getNotificationManager().notify(1, nBuilder.build());
    }
}