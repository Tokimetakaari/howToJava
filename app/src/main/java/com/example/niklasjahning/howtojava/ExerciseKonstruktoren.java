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

public class ExerciseKonstruktoren extends AppCompatActivity implements View.OnClickListener {

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
    private Intent next;
    boolean[] answerCorrect = new boolean[4];
    boolean[] answered = new boolean[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloze_default);
        setupDrawer();
        connectBurger();
        mySound = MediaPlayer.create(this,R.raw.sound);
        setupItems();
        setText();

    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText(R.string.exerciseKonstruktorenQ1);
                editText2.setVisibility(View.GONE);
                editText3.setVisibility(View.GONE);
                editText4.setVisibility(View.GONE);
                editText5.setVisibility(View.GONE);
                editText6.setVisibility(View.GONE);
                editText7.setVisibility(View.GONE);
                editText8.setVisibility(View.GONE);
                break;
            case 1:
                textView.setText(R.string.exerciseKonstruktorenQ2);
                break;
            case 2:
                textView.setText(getString(R.string.exerciseKonstruktorenQ3__1) +
                        getString(R.string.exerciseKonstruktorenQ3_2) +
                        getString(R.string.exerciseKonstruktorenQ3_3) +
                        getString(R.string.exerciseKonstruktorenQ3_4) +
                        getString(R.string.exerciseKonstruktorenQ3_5) +
                        getString(R.string.exerciseKonstruktorenQ3_6) +
                        getString(R.string.exerciseKonstruktorenQ3_7));
                break;
            case 3:
                textView.setText(R.string.exerciseKonstruktorenQ4);
                break;
            case 4:
                for (int j = 0; j < 4; j++)
                {
                    if (answerCorrect[j])
                    {numOfCorrectAnswers ++;}
                }
                textView.setText(getString(R.string.endScreenExercise,numOfCorrectAnswers,answered.length));
                editText1.setVisibility(View.INVISIBLE);
                editText1.setClickable(false);
                submit.setText(R.string.endScreenSubmit);
                i++;
                break;


        }
        resetEdit();
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
        next = new Intent(this,ExerciseSelectDatatypes2.class);
        nHelper = new NotificationHelper(this);

    }


    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseKonstruktoren.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseKonstruktoren.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseKonstruktoren.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseKonstruktoren.this, Theory.class);
                        intent.putExtra("txt_num", 61);
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
    public void onClick(View view) {
        if (view.getId() == R.id.cloze_submit_button) {
            if ((!editText1.getText().toString().isEmpty()) && (i <= 4)) {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();

            } else if (i > 4) {
                if (numOfCorrectAnswers >=  answered.length /2) {
                    mySound.start();
                    update();
                    sendNotification(getString(R.string.notifyTitle9), getString(R.string.notifyMessage), next);
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
                PlayMenu.unlockLevelNumber=9;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(9);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void checkCorrectAnswers() {

        switch (i) {
            case 0:
                if (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA1))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA1A1)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA1A2)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA1A3)))) {
                    answerCorrect[i] = true;
                }
            case 1:
                if (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA2))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA2A1)))) {
                    answerCorrect[i] = true;
                }
                break;
            case 2:
                if (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA3))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA3A1)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA3A2)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA3A3)))) {
                    answerCorrect[i] = true;

                }
                break;
            case 3:
                if (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA4))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA4A1)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA4A2)))
                        || (editText1.getText().toString().equals(getString(R.string.exerciseKonstruktorenA4A3)))) {
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
