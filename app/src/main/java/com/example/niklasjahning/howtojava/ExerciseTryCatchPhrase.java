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

public class ExerciseTryCatchPhrase extends AppCompatActivity implements View.OnClickListener {



    MediaPlayer mySound;
    TextView textView;
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers = 0;

    String solution1 = "Try-Catch-Phrase";
    String alternative1_1 = "try-catch-phrase";
    String alternative1_2 = "Try Catch Phrase";
    String alternative1_3 = "try catch phrase";
    String alternative1_4 = "Try catch phrase";
    String solution2 = "try";
    String alternative2_1 = "Try";
    String solution3 = "Catch";
    String alternative3_1 = "catch";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent intent;
    //Hier die Strings für die Notification festlegen
    String title = "Congratulation, du hast Übung 16 bestanden";
    String message = "Zur nächsten Übung hier klicken!";
    private NotificationHelper nHelper;
    private int questionsQ = 3;
    private Intent next;



    boolean[] answerCorrect = new boolean[3];
    boolean[] answered = new boolean[3];


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
                textView.setText("Um den Absturz eines Programmes zu verhindern verwendet und Fehler anzufangen, verwendet man einen ________ .");
                editText2.setVisibility(View.GONE);
                editText3.setVisibility(View.GONE);
                editText4.setVisibility(View.GONE);
                editText5.setVisibility(View.GONE);
                editText6.setVisibility(View.GONE);
                editText7.setVisibility(View.GONE);
                editText8.setVisibility(View.GONE);
                break;
            case 1:
                textView.setText("Wird keine Exeption 'gethrowed' wird der Code im ________-Phrase ausgeführt");
                break;
            case 2:
                textView.setText("Wenn ein Fehler, (welcher in der Bedingung definiert ist), gefunden wurde, wird der Code im _______-Phrase ausgeführt.");
                break;
            case 3:
                for (int j = 0; j < 3; j++)
                {
                    if (answerCorrect[j])
                    {numOfCorrectAnswers ++;}
                }
                textView.setText("Sie haben " + numOfCorrectAnswers +" richtig beantwortet");
                editText1.setVisibility(View.INVISIBLE);
                editText1.setClickable(false);
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
        nHelper = new NotificationHelper(this);
        next = new Intent(this,ExerciseSelectDatatypes2.class);

    }


    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        intent = new Intent(ExerciseTryCatchPhrase.this, PlayMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.theory_menu:
                        intent = new Intent(ExerciseTryCatchPhrase.this, TheoryMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.setting_menu:
                        intent = new Intent(ExerciseTryCatchPhrase.this, SettingsMenu.class);
                        startActivity(intent);
                        break;
                    case R.id.moveToTheory:
                        finish();
                        intent = new Intent(ExerciseTryCatchPhrase.this, DataInJava.class);

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
                    sendNotification(title, message, next);
                }
                finish();

            }
        }
    }

    private void checkCorrectAnswers() {

//        Falls mehrere Schreibweise zählen einfach eine weitere Bedingung mit || hinzufügen editText1.getText().toString().equals(alternative1)

        switch (i) {
            case 0:
                if (editText1.getText().toString().equals(solution1) || (editText1.getText().toString().equals(alternative1_1)) || (editText1.getText().toString().equals(alternative1_2)) || (editText1.getText().toString().equals(alternative1_3)) || (editText1.getText().toString().equals(alternative1_4))) {
                    answerCorrect[i] = true;
                }
            case 1:
                if ((editText1.getText().toString().equals(solution2)) || (editText1.getText().toString().equals(alternative2_1)) ) {
                    answerCorrect[i] = true;
                }
                break;
            case 2:
                if (editText1.getText().toString().equals(solution3) || (editText1.getText().toString().equals(alternative3_1)) ) {
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
