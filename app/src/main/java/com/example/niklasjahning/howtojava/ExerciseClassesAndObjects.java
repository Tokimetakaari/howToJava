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
    String solution1 = "Muster";
    String alternative1_1 = "muster";
    String alternative1_2 = "Vorlagen";
    String alternative1_3 ="vorlagen";
    String alternative1_4 = "Konzepte";
    String alternative1_5 = "konzepte";
    String solution2 = "Eigenschaften";
    String alternative2_1 = "eigenschaften";
    String solution3 = "public class Dog{}";
    String alternative3_1 = "public class Dog {}";
    String solution4 = "Dog umberto = new Dog();";
    String alternative4_1 = "Dog umberto=new Dog()";
    String alternative4_2 = "Dog umberto =new Dog()";
    String alternative4_3 = "Dog umberto= new Dog()";
    String alternative4_4 = "Dog umberto = new Dog ()";
    String alternative4_5 = "Dog umberto=new Dog ()";
    String alternative4_6 = "Dog umberto =new Dog ()";
    String alternative4_7 = "Dog umberto= new Dog ()";

    //Hier die Strings für die Notification festlegen
    String title = "Congrats";
    String message = "Du hast Übung 3 bestanden";
    private NotificationHelper nHelper;
    private int questionsQ = 4;
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
                PlayMenu.unlockLevelNumber = 3;
                if (numOfCorrectAnswers >=  questionsQ /2) {
                    mySound.start();
                    sendNotification(title, message, next);
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
                textView.setText("Klassen dienen als ________ für Objekte/ Instanzen.");
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
                answer5.setVisibility(View.GONE);
                answer6.setVisibility(View.GONE);
                answer7.setVisibility(View.GONE);
                answer8.setVisibility(View.GONE);
                break;
            case 1: textView.setText("Klassen beschreiben die ________ von Objekten, bzw. Instanzen, wie der Hund zum Beispiel Stöckchen holt und bellt.");
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
                answer5.setVisibility(View.GONE);
                answer6.setVisibility(View.GONE);
                answer7.setVisibility(View.GONE);
                answer8.setVisibility(View.GONE);
                break;
            case 2:
                textView.setText("Schreiben Sie den Code, um die Klasse Hund zu erzeugen ein Objekt/ eine Instanz dieser Klasse mit Namen Umberto zu erstellen");
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
                answer5.setVisibility(View.GONE);
                answer6.setVisibility(View.GONE);
                answer7.setVisibility(View.GONE);
                answer8.setVisibility(View.GONE);
                break;
            case 3:
                textView.setText("Schreiben Sie den Code, um ein Objekt/ eine Instanz der zuvor erstellten Klasse 'Dog' mit Namen 'Umberto' zu erstellen");
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
                textView.setText("Sie haben " + numOfCorrectAnswers +" richtig beantwortet");
                answer1.setVisibility(View.INVISIBLE);
                answer1.setClickable(false);
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
                if (answer1.getText().toString().equals(solution1) || (answer1.getText().toString().equals(alternative1_1)) || (answer1.getText().toString().equals(alternative1_2)) || (answer1.getText().toString().equals(alternative1_3)) || (answer1.getText().toString().equals(alternative1_4)) || (answer1.getText().toString().equals(alternative1_5))) {
                    answerCorrect[i] = true;
                }
                break;
            case 1:
                if (answer1.getText().toString().equals(solution2) || (answer1.getText().toString().equals(alternative2_1))) {
                    answerCorrect[i] = true;
                }
                break;
            case 2:
                if (answer1.getText().toString().equals(solution3) || (answer1.getText().toString().equals(alternative3_1))) {
                    answerCorrect[i] = true;
                }
                break;
            case 3:
                if (answer1.getText().toString().equals(solution4) || (answer1.getText().toString().equals(alternative4_1)) || (answer1.getText().toString().equals(alternative4_2)) || (answer1.getText().toString().equals(alternative4_3)) || (answer1.getText().toString().equals(alternative4_4)) || (answer1.getText().toString().equals(alternative4_5)) || (answer1.getText().toString().equals(alternative4_6)) || (answer1.getText().toString().equals(alternative4_7))) {
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
