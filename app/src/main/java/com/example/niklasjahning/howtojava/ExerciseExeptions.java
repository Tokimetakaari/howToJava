package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseExeptions extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mySound;

    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textQuest;
    Button submit;

    int i = 0;
    int numOfCorrectAnswers=0;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    //Hier die Strings für die Notification festlegen
    String title = "Congrats";
    String message = "Du hast Übung 1 bestanden";
    private NotificationHelper nHelper;
    private int questionsQ = 5;
    private Intent next;




    boolean[] answerCorrect = new boolean[5];
    boolean[] answered = new boolean[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reassemble_layout_default);
        setupItems();
        setupDrawer();
        connectBurger();
        mySound = MediaPlayer.create(this,R.raw.sound);
        setText();
    }

    private void setupItems() {

        textView1 = findViewById(R.id.reassemble_text_1);
        textView2 = findViewById(R.id.reassemble_text_2);
        textView3 = findViewById(R.id.reassemble_text_3);
        textView4 = findViewById(R.id.reassemble_text_4);
        textView5 = findViewById(R.id.reassemble_text_5);
        textView6 = findViewById(R.id.reassemble_text_6);
        textQuest = findViewById(R.id.reassemble_question);
        spinner1 = findViewById(R.id.reassemble_spinner_1);
        spinner2 = findViewById(R.id.reassemble_spinner_2);
        spinner3 = findViewById(R.id.reassemble_spinner_3);
        spinner4 = findViewById(R.id.reassemble_spinner_4);
        spinner5 = findViewById(R.id.reassemble_spinner_5);
        spinner6 = findViewById(R.id.reassemble_spinner_6);
        submit = findViewById(R.id.reassemble_submit_button);
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
                        intent = new Intent(ExerciseExeptions.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseExeptions.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseExeptions.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseExeptions.this, DataInJava.class);
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

    private int[] getSelectedItemPositions() {
        int[] itemPositions = new int[6];

        itemPositions[0] = Integer.parseInt(spinner1.getSelectedItem().toString());
        itemPositions[1] = Integer.parseInt(spinner2.getSelectedItem().toString());
        itemPositions[2] = Integer.parseInt(spinner3.getSelectedItem().toString());
        itemPositions[3] = Integer.parseInt(spinner4.getSelectedItem().toString());
        itemPositions[4] = Integer.parseInt(spinner5.getSelectedItem().toString());
        itemPositions[5] = Integer.parseInt(spinner6.getSelectedItem().toString());

        return itemPositions;
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.reassemble_submit_button)
        {
            if ( (spinner1.getSelectedItem() != null || spinner2.getSelectedItem() != null || spinner3.getSelectedItem() != null || spinner4.getSelectedItem() != null) && (i <=5))
            {
                checkCorrectAnswers(getSelectedItemPositions());
                answered[i] = true;
                i++;
                setText();
            }
            else if (i>5)
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
                PlayMenu.unlockLevelNumber=18;
                StorageEntry storageEntry = MainActivity.database.daoAccess().getConfiqEntry("unlockLevel");
                storageEntry.setValue(PlayMenu.unlockLevelNumber);
                MainActivity.database.daoAccess().updateEntries(storageEntry);
            }
        }).start();
    }

    private void setText() {
        switch (i) {
            case 0:
                textQuest.setText("Deine ");
                textView1.setText("Deine");
                textView2.setText("Alter");
                textView3.setText("Say");
                textView4.setText("What");
                textView5.setText("What");
                textView6.setText("What");
                break;
            case 1:
                textQuest.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
            case 2:
                textQuest.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
            case 3:
                textQuest.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
            case 4:
                textQuest.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
            case 5:
                textQuest.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
            case 6:
                countCorrectAnswers();
                textQuest.setText("Sie haben " + numOfCorrectAnswers + " Fragen von " + questionsQ + " richtig beantwortet.");
                textView1.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                textView4.setVisibility(View.GONE);
                textView5.setVisibility(View.GONE);
                textView6.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                submit.setText("Zurück zum Hauptmenü");
                i++;

                break;
            default:
                break;


        }
        resetSpinner();
    }

    private void resetSpinner ()
    {
        spinner1.setSelected(false);
        spinner2.setSelected(false);
        spinner3.setSelected(false);
        spinner4.setSelected(false);
        spinner5.setSelected(false);
        spinner6.setSelected(false);

    }

    private void checkCorrectAnswers(int[] itemPostions) {
        switch (i)
        {
            case 0: if (itemPostions[0] == 1) {
                if (itemPostions[1] == 2) {
                    if (itemPostions[2] == 3) {
                        if (itemPostions[3] == 4) {
                            if (itemPostions[4] == 5) {
                                if (itemPostions[5] == 6) {
                                    answerCorrect[i] = true;
                                }
                            }
                        }
                    }
                }
            }

                break;
            default:
                break;
        }
    }

    private int countCorrectAnswers() {
        for (int j = 0; j < 5; j++)
        {
            if (answerCorrect[j])
            {numOfCorrectAnswers ++;}
        }
        return numOfCorrectAnswers;
    }

    public void sendNotification(String title, String message, Intent next) {
        android.support.v4.app.NotificationCompat.Builder nBuilder = nHelper.getChannelNotification(title, message, next);
        nHelper.getNotificationManager().notify(1, nBuilder.build());
    }

}
