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
    //Hier die Strings für die Notification festlegen
    String title = "Congratulation, du hast Übung 1 bestanden";
    String message = "Zur nächsten Übung hier klicken!";
    private NotificationHelper nHelper;
    private Intent next;


    boolean[] answerCorrect = new boolean[5];
    boolean[] answered = new boolean[5];

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
        next = new Intent(this,ExerciseSelectDatatypes2.class);
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
            if ( (box1.isChecked()|| box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <=5))
            {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();
            }
            else if (i>5)
            {

                if (numOfCorrectAnswers >= answered.length /2) {
                    mySound.start();
                    sendNotification(title, message,next);
                    update();
                }
                finish();
            }
        }
    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText("Was gibt es nicht ?");
                box1.setText("if() {}");
                box2.setText("else() {}");
                box3.setText("if else() {}");
                box4.setText("else if () {}");
                break;
            case 1:
                textView.setText("Welchen Datentyp würden Sie verwenden um den Wert true zu speichern?");
                box1.setText("int");
                box2.setText("String");
                box3.setText("boolean");
                box4.setText("byte");
                break;
            case 2:
                textView.setText("Welchen Datentyp würden Sie verwenden um den Wert 1,3 zu speichern?");
                box1.setText("double");
                box2.setText("int");
                box3.setText("byte");
                box4.setText("long");
                break;
            case 3:
                textView.setText("Welchen Datentyp würden Sie verwenden um den Buchstaben c zu speichern?");
                box1.setText("char");
                box2.setText("String");
                box3.setText("int");
                box4.setText("boolean");
                break;
            case 4:
                textView.setText("Welchen Datentyp würden Sie verwenden um das Wort Java zu speichern?");
                box1.setText("char");
                box2.setText("String");
                box3.setText("double");
                box4.setText("float");
                break;
            case 5:
                countCorrectAnswers();
                textView.setText("Sie haben " + numOfCorrectAnswers + " Fragen von " + answered.length + "richtig beantwortet.");
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
        for (int j = 0; j < 5; j++)
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


