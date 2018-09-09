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

public class ExerciseClassesAndObjects2 extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mySound;

    TextView textView;
    CheckBox box1, box2, box3, box4;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers=0;

    boolean[] answerCorrect = new boolean[3];
    boolean[] answered = new boolean[3];

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;

    //Hier die Strings für die Notification festlegen
    String title = "Congrats";
    String message = "Du hast Übung 4 bestanden";
    private NotificationHelper nHelper;
    private int questionsQ = 3;
    private Intent next;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox_layout_default);
        setupDrawer();
        connectBurger();
        setupItems();
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
        nHelper = new NotificationHelper(this);
        submit.setOnClickListener(this);
        next = new Intent(this,ExerciseSelectDatatypes2.class);
    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseClassesAndObjects2.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseClassesAndObjects2.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseClassesAndObjects2.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseClassesAndObjects2.this, ClassesAndObjects.class);
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
            if ( (box1.isChecked()|| box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <=3))
            {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();
            }
            else if (i>3)
            {
                PlayMenu.unlockLevelNumber = 4;
                if (numOfCorrectAnswers >=  questionsQ /2) {
                    mySound.start();
                    update();
                    sendNotification(title, message, next);
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
                PlayMenu.unlockLevelNumber=4;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(4);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void setText()
    {
        switch (i) {
            case 0:
                textView.setText("Welches Sichtbarkeitsattribut besagt, dass ein Objekt oder eine Variable nur innerhalb der aktuellen Klasse sichtbar ist?");
                box1.setText("private");
                box2.setText("protected");
                box3.setText("public");
                box4.setVisibility(View.GONE);
                break;
            case 1:
                textView.setText("Welches Sichtbarkeitsattribut besagt, dass ein Objekt oder eine Variable im gesamten Programm sichtbar ist?");
                box1.setText("protected");
                box2.setText("public");
                box3.setText("private");
                box4.setVisibility(View.GONE);
                break;
            case 2:
                textView.setText("Objekte oder Variablen, welche nach dem Erstellen nicht mehr verändert werden können, sind: ");
                box1.setText("final");
                box2.setText("static");
                box3.setText("protected");
                box4.setVisibility(View.GONE);
                break;
            case 3:
                countCorrectAnswers();
                textView.setText("Sie haben " + numOfCorrectAnswers + " Fragen richtig beantwortet.");
                box1.setClickable(false);
                box1.setVisibility(View.INVISIBLE);
                box2.setClickable(false);
                box2.setVisibility(View.INVISIBLE);
                box3.setClickable(false);
                box3.setVisibility(View.INVISIBLE);
                box4.setClickable(false);
                box4.setVisibility(View.INVISIBLE);
                submit.setText("Zurück zum Hauptmenü");
                i++;

                break;
            default:
                break;
        }
        resetCheckbox();
    }

    private int countCorrectAnswers() {
        for (int j = 0; j < 3; j++)
        {
            if (answerCorrect[j])
            {numOfCorrectAnswers ++;}
        }
        return numOfCorrectAnswers;
    }


    private void resetCheckbox () {
        box1.setChecked(false);
        box2.setChecked(false);
        box3.setChecked(false);
        box4.setChecked(false);
    }

    private void checkCorrectAnswers() {
        switch (i)
        {
            case 0: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 1: if (!box1.isChecked() && box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 2: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked())
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
