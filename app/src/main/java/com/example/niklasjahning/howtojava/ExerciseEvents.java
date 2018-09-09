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

public class ExerciseEvents extends AppCompatActivity implements View.OnClickListener {


    MediaPlayer mySound;
    TextView textView;
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers = 0;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;

    private NotificationHelper nHelper;
    private int questionsQ = 4;
    private Intent next;


    boolean[] answerCorrect = new boolean[4];
    boolean[] answered = new boolean[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloze_default);
        setupDrawer();
        connectBurger();
        setupItems();
        setText();


    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText(R.string.exerciseEventsQ1);
                editText2.setVisibility(View.GONE);
                editText3.setVisibility(View.GONE);
                editText4.setVisibility(View.GONE);
                editText5.setVisibility(View.GONE);
                editText6.setVisibility(View.GONE);
                editText7.setVisibility(View.GONE);
                editText8.setVisibility(View.GONE);
                break;
            case 1:
                textView.setText(R.string.exerciseEventsQ2);
                break;
            case 2:
                textView.setText(R.string.exerciseEventsQ3);
                break;
            case 3:
                textView.setText(R.string.exerciseEventsQ4);
                break;
            case 4:
                countCorrectAnswers();
                textView.setText(getString(R.string.endScreenExercise,numOfCorrectAnswers,answered.length));
                editText1.setVisibility(View.INVISIBLE);
                editText1.setClickable(false);
                submit.setText(R.string.endScreenSubmit);
                i++;
                break;


        }
        resetEdit();
    }

    private void countCorrectAnswers() {
        for (int j = 0; j < 4; j++)
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
        next = new Intent(this,ExerciseTryCatchPhrase.class);
        nHelper = new NotificationHelper(this);

    }


    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseEvents.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseEvents.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseEvents.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseEvents.this, InterfacesAndEvents.class);
                        startActivity(intent);
                        break;
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),getString(R.string.credits_text),Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });}

    private void update()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayMenu.unlockLevelNumber=14;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }


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
            if ((!editText1.getText().toString().isEmpty()) && (i <= 4)) {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();

            } else if (i > 4) {
                if (numOfCorrectAnswers >=  questionsQ /2) {
                    mySound.start();
                    sendNotification(getString(R.string.notifyTitle14),getString(R.string.notifyMessage),next);
                    update();
                }
                finish();

            }
        }
    }

    private void checkCorrectAnswers() {

//        Falls mehrere Schreibweise zählen einfach eine weitere Bedingung mit || hinzufügen editText1.getText().toString().equals(alternative1)

        switch (i) {
            case 0:
                if (editText1.getText().toString().equals(getString(R.string.exerciseEventsA1))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA1A1)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA1A2)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA1A3)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA1A4)))) {
                    answerCorrect[i] = true;
                }
            case 1:
                if ((editText1.getText().toString().equals(getString(R.string.exerciseEventsA2)) )
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA2A2)))) {
                    answerCorrect[i] = true;
                }
                break;
            case 2:
                if (editText1.getText().toString().equals(getString(R.string.exerciseEventsA3))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA3A1)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA3A2)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA3A3)))) {
                    answerCorrect[i] = true;

                }
                break;
            case 3:
                if (editText1.getText().toString().equals(getString(R.string.exerciseEventsA4))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseEventsA4A1)))) {
                    answerCorrect[i] = true;

                }
                break;
            default:
                break;



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
