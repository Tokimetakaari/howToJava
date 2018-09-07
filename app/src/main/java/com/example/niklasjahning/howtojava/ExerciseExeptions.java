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
    AdapterView.OnItemClickListener onItemClickListener;



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
        spinner1 = findViewById(R.id.reassemble_spinner_1);
        spinner2 = findViewById(R.id.reassemble_spinner_2);
        spinner3 = findViewById(R.id.reassemble_spinner_3);
        spinner4 = findViewById(R.id.reassemble_spinner_4);
        spinner5 = findViewById(R.id.reassemble_spinner_5);
        spinner6 = findViewById(R.id.reassemble_spinner_6);
        submit = findViewById(R.id.reassemble_submit_button);
        nHelper = new NotificationHelper(this);
        submit.setOnClickListener(this);
        spinner1.setOnItemClickListener(onItemClickListener);
        spinner2.setOnItemClickListener(onItemClickListener);
        spinner3.setOnItemClickListener(onItemClickListener);
        spinner4.setOnItemClickListener(onItemClickListener);
        spinner5.setOnItemClickListener(onItemClickListener);
        spinner6.setOnItemClickListener(onItemClickListener);

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
        int[] itemPositions = new int[2];

        itemPositions[0] = spinnerEggSize.getSelectedItemPosition();
        itemPositions[1] = spinnerDoneness.getSelectedItemPosition();

        return itemPositions;
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.checkbox_submit_button)
        {
            if ( (spinner1.getSelectedItem() != null || spinner2.getSelectedItem() != null || spinner3.getSelectedItem() != null || spinner4.getSelectedItem() != null) && (i <=5))
            {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();
            }
            else if (i>5)
            {
                PlayMenu.positionOfNewLevel = 1;
                if (numOfCorrectAnswers >=  questionsQ /2) {
                    mySound.start();
                    sendNotification(title, message);
                }
                finish();
            }
        }
    }

    private void setText() {
        switch (i) {
            case 0:
                textQuest.setText("");
                textView1.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
                break;
            case 1:
                textView2.setText("");
            case 2:
                textView3.setText("");
            case 3:
                textView4.setText("");
            case 4:
                textView5.setText("");
            case 5:
                textView6.setText("");
            case 6:
                countCorrectAnswers();
                textQuest.setText("Sie haben " + numOfCorrectAnswers + " Fragen von " + questionsQ + "richtig beantwortet.");
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


        }
    }

    private void initSpinner(Spinner spinner, int arrayID) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                ExerciseExeptions.this, arrayID,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            {
                int[] itemPositions = getSelectedItemPositions();
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
            }
        });
    }


    private int countCorrectAnswers() {
        for (int j = 0; j < 5; j++)
        {
            if (answerCorrect[j])
            {numOfCorrectAnswers ++;}
        }
        return numOfCorrectAnswers;
    }

}
