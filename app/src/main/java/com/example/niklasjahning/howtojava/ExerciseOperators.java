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

public class ExerciseOperators extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    CheckBox box1, box2, box3, box4;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers=0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    private Intent next;
    MediaPlayer mySound;
    private NotificationHelper nHelper;
    boolean[] answerCorrect = new boolean[7];
    boolean[] answered = new boolean[7];

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

    private void setupItems()
    {
        textView = findViewById(R.id.checkbox_layout_4_question);
        box1 = findViewById(R.id.checkbox_layout_4_checkbox1);
        box2 = findViewById(R.id.checkbox_layout_4_checkbox2);
        box3 = findViewById(R.id.checkbox_layout_4_checkbox3);
        box4 = findViewById(R.id.checkbox_layout_4_checkbox4);
        submit = findViewById(R.id.checkbox_submit_button);
        submit.setOnClickListener(this);
        nHelper = new NotificationHelper(this);
        next = new Intent(this,ExerciseIfElse.class);
    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseOperators.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseOperators.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseOperators.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseOperators.this, LogicAndConditions.class);
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
        mDrawerLayout = findViewById(R.id.burgerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void update()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PlayMenu.unlockLevelNumber=5;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkbox_submit_button) {
            if ((box1.isChecked() || box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <= 7)) {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();

            } else if (i >= 7) {
                if (numOfCorrectAnswers >= answerCorrect.length / 2) {
                    mySound.start();
                    sendNotification(getString(R.string.notifyTitle5), getString(R.string.notifyMessage), next);
                    update();
                    finish();

                }
            }
        }
    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText(R.string.exerciseOperatorsQ1);
                box1.setText(R.string.exerciseOperatorsQ1A1);
                box2.setText(R.string.exerciseOperatorsQ1A2);
                box3.setText(R.string.exerciseOperatorsQ1A3);
                box4.setText(R.string.exerciseOperatorsQ1A4);
                break;
            case 1:
                textView.setText(R.string.exerciseOperatorsQ2);
                box1.setText(R.string.exerciseOperatorsQ2A1);
                box2.setText(R.string.exerciseOperatorsQ2A2);
                box3.setText(R.string.exerciseOperatorsQ2A3);
                box4.setText(R.string.exerciseOperatorsQ2A4);
                break;
            case 2:
                textView.setText(R.string.exerciseOperatorsQ3);
                box1.setText(R.string.exerciseOperatorsQ3A1);
                box2.setText(R.string.exerciseOperatorsQ3A2);
                box3.setText(R.string.exerciseOperatorsQ3A3);
                box4.setText(R.string.exerciseOperatorsQ3A4);
                break;
            case 3:
                textView.setText(R.string.exerciseOperatorsQ4);
                box1.setText(R.string.exerciseOperatorsQ4A1);
                box2.setText(R.string.exerciseOperatorsQ4A2);
                box3.setText(R.string.exerciseOperatorsQ4A3);
                box4.setText(R.string.exerciseOperatorsQ4A4);
                break;
            case 4:
                textView.setText(R.string.exerciseOperatorsQ5);
                box1.setText(R.string.exerciseOperatorsQ5A1);
                box2.setText(R.string.exerciseOperatorsQ5A2);
                box3.setText(R.string.exerciseOperatorsQ5A3);
                box4.setText(R.string.exerciseOperatorsQ5A4);
                break;


            case 5:
                textView.setText(R.string.exerciseOperatorsQ6);
                box1.setText(R.string.exerciseOperatorsQ6A1);
                box2.setText(R.string.exerciseOperatorsQ6A2);
                box3.setText(R.string.exerciseOperatorsQ6A3);
                box4.setText(R.string.exerciseOperatorsQ6A4);
                break;

            case 6:
                textView.setText(R.string.exerciseOperatorsQ7);
                box1.setText(R.string.exerciseOperatorsQ7A1);
                box2.setText(R.string.exerciseOperatorsQ7A2);
                box3.setText(R.string.exerciseOperatorsQ7A3);
                box4.setText(R.string.exerciseOperatorsQ7A4);
                break;
            case 7:
                countCorrectAnswers();
                textView.setText(getString(R.string.endScreenExercise,numOfCorrectAnswers,answered.length));
                box1.setVisibility(View.INVISIBLE);
                box1.setClickable(false);
                box2.setVisibility(View.INVISIBLE);
                box2.setClickable(false);
                box3.setVisibility(View.INVISIBLE);
                box3.setClickable(false);
                box4.setVisibility(View.INVISIBLE);
                box4.setClickable(false);
                submit.setText(R.string.endScreenSubmit);
                i++;
                break;

            default:

                break;
        }
        resetCheckbox();
    }

    private void resetCheckbox ()
    {
        box1.setChecked(false);
        box2.setChecked(false);
        box3.setChecked(false);
        box4.setChecked(false);
    }

    private void checkCorrectAnswers()
    {
        switch (i)
        {
            case 0: if (!box1.isChecked() && box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
            case 1: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 2: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 3: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 4: if (!box1.isChecked() && box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
            case 5: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
            case 6: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;

            default:

                break;
        }
    }

    private void countCorrectAnswers() {
        for (int j = 0; j < 7; j++)
        {
            if (answerCorrect[j])
            {numOfCorrectAnswers ++;}
        }
    }

    public void sendNotification(String title, String message, Intent next) {
        NotificationCompat.Builder nBuilder = nHelper.getChannelNotification(title, message, next);
        nHelper.getNotificationManager().notify(1, nBuilder.build());
    }






}
