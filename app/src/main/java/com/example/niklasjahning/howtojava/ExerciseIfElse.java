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
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseIfElse extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mySound;
    TextView textView;
    CheckBox box1, box2, box3, box4;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers=0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    private NotificationHelper nHelper;
    private Intent next;
    boolean[] answerCorrect = new boolean[2];
    boolean[] answered = new boolean[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox_layout_default);
        setupItems();
        setupDrawer();
        connectBurger();
        mySound = MediaPlayer.create(this,R.raw.sound);
        setText();
    }


    private void setupItems() {
        textView = findViewById(R.id.checkbox_layout_4_question);
        box1 = findViewById(R.id.checkbox_layout_4_checkbox1);
        box2 = findViewById(R.id.checkbox_layout_4_checkbox2);
        box3 = findViewById(R.id.checkbox_layout_4_checkbox3);
        box4 = findViewById(R.id.checkbox_layout_4_checkbox4);
        submit = findViewById(R.id.checkbox_submit_button);
        nHelper = new NotificationHelper(this);
        submit.setOnClickListener(this);
        next = new Intent(this,ExerciseArray.class);
    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseIfElse.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseIfElse.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseIfElse.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseIfElse.this, LogicAndConditions.class);
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
    public void onClick(View view)
    {
        if(view.getId() == R.id.checkbox_submit_button)
        {
            if ( (box1.isChecked()|| box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <=2))
            {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();
            }
            else if (i>2)
            {

                if (numOfCorrectAnswers >= answered.length /2) {
                    mySound.start();
                    sendNotification(getString(R.string.notifyTitle6), getString(R.string.notifyMessage),next);
                    update();
                }
                finish();
            }
        }
    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText(R.string.exerciseIfElseQ1);
                box1.setText(R.string.exerciseIfElseQ1A1);
                box2.setText(R.string.exerciseIfElseQ1A2);
                box3.setText(R.string.exerciseIfElseQ1A3);
                box4.setText(R.string.exerciseIfElseQ1A4);
                break;
            case 1:
                textView.setText(R.string.exerciseIfElseQ2);
                box1.setText(R.string.exerciseIfElseQ2A1);
                box2.setText(R.string.exerciseIfElseQ2A2);
                box3.setText(R.string.exerciseIfElseQ2A3);
                box4.setText(R.string.exerciseIfElseQ2A4);
                break;
            case 2:
                countCorrectAnswers();
                textView.setText(getString(R.string.endScreenExercise,numOfCorrectAnswers,answered.length));
                box1.setClickable(false);
                box1.setVisibility(View.INVISIBLE);
                box2.setClickable(false);
                box2.setVisibility(View.INVISIBLE);
                box3.setClickable(false);
                box3.setVisibility(View.INVISIBLE);
                box4.setClickable(false);
                box4.setVisibility(View.INVISIBLE);
                submit.setText(R.string.endScreenSubmit);
                i++;

                break;
            default:
                break;
        }
        resetCheckbox();
    }

    private void update()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayMenu.unlockLevelNumber=6;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void resetCheckbox ()
    {
        box1.setChecked(false);
        box2.setChecked(false);
        box3.setChecked(false);
        box4.setChecked(false);
    }

    private int countCorrectAnswers() {
        for (int j = 0; j < 2; j++)
        {
            if (answerCorrect[j])
            {numOfCorrectAnswers ++;}
        }
        return numOfCorrectAnswers;
    }

    private void checkCorrectAnswers() {
        switch (i)
        {
            case 0: if (!box1.isChecked() && box2.isChecked() && box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 1: if (!box1.isChecked() && !box2.isChecked() && box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 2: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 3: if (box1.isChecked() || box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 4: if (!box1.isChecked() && box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            default:
                break;
        }
    }

    public void sendNotification(String title, String message, Intent next) {
        NotificationCompat.Builder nBuilder = nHelper.getChannelNotification(title, message, next);
        nHelper.getNotificationManager().notify(1, nBuilder.build());
    }

}


