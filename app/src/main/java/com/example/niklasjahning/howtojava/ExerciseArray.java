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

public class ExerciseArray extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mySound;
    TextView textView;
    CheckBox box1, box2, box3, box4;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers = 0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    //Hier die Strings für die Notification festlegen
    String title = "Congrats";
    String message = "Du hast Übung X bestanden";
    private NotificationHelper nHelper;
    private int questionsQ = 8;
    private Intent next;

    boolean[] answerCorrect = new boolean[8];
    boolean[] answered = new boolean[8];

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
                        intent = new Intent(ExerciseArray.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseArray.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseArray.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseArray.this, Arrays.class);
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
            if ( (box1.isChecked()|| box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <=8))
            {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();
            }
            else if (i>8)
            {
                PlayMenu.unlockLevelNumber = 1;
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
                PlayMenu.unlockLevelNumber=6;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(6);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText(" Kreuzen Sie die richtig initialisierten Arrays an.");
                box1.setText(" int[] gradeList = {1, 2, 3, 4, 5, 6}");
                box2.setText(" String names = new names[]");
                box3.setText(" Array<numbers> String = new array<numbers>");
                box4.setText(" double[] list = new double[10]");
                break;
            case 1:
                textView.setText(" Arrays verwendet man um:");
                box1.setText(" eine Ansammlung von Werte des gleichen Datentypes zu speichern");
                box2.setText(" verschiedene Klassenvariablen zusammenzufassen, der Datentyp kann unbeachtet bleiben");
                box3.setVisibility(View.GONE);
                box4.setVisibility(View.GONE);
                break;
            case 2:
                textView.setText(" Wie lässt sich auf die Länge eines Arrays zugreifen?");
                box1.setText(" arrayName.length");
                box2.setText(" arrayName.length()");
                box3.setText(" arrayName.size");
                box4.setText(" arrayName.size()");
                break;
            case 3:
                textView.setText(" Wie viele Reihen, bzw. Spalten hat dieser zweidimensionale Array: int[][] multi = new int[5][10]");
                box1.setText(" Der Array hat 5 Reihen und 10 Spalten.");
                box2.setText(" Der Array hat 5 Spalten und 10 Reihen.");
                box3.setVisibility(View.GONE);
                box4.setVisibility(View.GONE);
                break;
            case 4:
                textView.setText(" Was ist der Vorteil einer ArrayList im Gegensatz zu dem Array?");
                box1.setText(" Die ArrayList kann auch unterschiedliche Datentypen in einer List speichern.");
                box2.setText(" Die Größe/ Länge der ArrayList ist veränderbar.");
                box3.setText(" Man kann Elemente auch nach der Indizialisierung hinzufügen oder entfernen.");
                box4.setVisibility(View.GONE);
                break;
            case 5:
                textView.setText(" Welche ArrayLists sind korrekt initialisiert?");
                box1.setText(" ArrayList<int> myList = new ArrayList<int>(50)");
                box2.setText(" ArrayList<String> groceries = new ArrayList<String>(200)");
                box3.setText(" ArrayLists myArrayList = new ArrayList()");
                box4.setText(" ArrayLists<Double> myDoubleList = new ArrayList()");
                break;
            case 6:
                textView.setText(" Wo beginnen die Indizes des Arrays, als auch der ArrayList?");
                box1.setText(" bei 0");
                box2.setText(" bei 1");
                box3.setVisibility(View.GONE);
                box4.setVisibility(View.GONE);
                break;
            case 7:
                textView.setText(" Wie lässt man sich die Anzahl der Elemente einer ArrayList ausgeben?");
                box1.setText(" myArrayList.length");
                box2.setText(" myArrayList.length()");
                box3.setText(" myArrayList.size");
                box4.setText(" myArrayList.size()");
                break;
            case 8:
                countCorrectAnswers();
                textView.setText("Sie haben " + numOfCorrectAnswers + " Fragen von " + questionsQ + "richtig beantwortet.");
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


    private void resetCheckbox ()
    {
        box1.setChecked(false);
        box2.setChecked(false);
        box3.setChecked(false);
        box4.setChecked(false);
    }

    private int countCorrectAnswers() {
        for (int j = 0; j < 8; j++)
        {
            if (answerCorrect[j])
            {numOfCorrectAnswers ++;}
        }
        return numOfCorrectAnswers;
    }

    private void checkCorrectAnswers() {
        switch (i)
        {
            case 0: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked()) {
                answerCorrect[i] = true;
            }
                break;
            case 1: if (box1.isChecked() && !box2.isChecked()) {
                answerCorrect[i] = true;
            }
                break;
            case 2: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked()) {
                answerCorrect[i] = true;
            }
                break;
            case 3: if (box1.isChecked() || !box2.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 4: if (!box1.isChecked() && box2.isChecked() && box3.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 5: if (!box1.isChecked() && box2.isChecked() && box3.isChecked() && !box4.isChecked()) {
                answerCorrect[i] = true;
            }
                break;
            case 6: if (box1.isChecked() && !box2.isChecked()) {
                answerCorrect[i] = true;
            }
                break;
            case 7: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked()) {
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
