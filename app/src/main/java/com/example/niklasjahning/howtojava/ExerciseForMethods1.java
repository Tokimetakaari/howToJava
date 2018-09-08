package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseForMethods1 extends AppCompatActivity implements View.OnClickListener {
        TextView textView;
        CheckBox box1, box2, box3, box4;
        Button submit;
        int i = 0;
        int numOfCorrectAnswers=0;
        private DrawerLayout mDrawerLayout;
        private ActionBarDrawerToggle mToggle;
        NavigationView burger;
        private Intent intent;


        boolean[] answerCorrect = new boolean[5];
        boolean[] answered = new boolean[5];

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.checkbox_layout_default);
            setupDrawer();
            connectBurger();
            setupItems();
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
        }

        private void connectBurger() {
            burger = findViewById(R.id.test);
            burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.play_menu:
                            intent = new Intent(com.example.niklasjahning.howtojava.ExerciseForMethods1.this, PlayMenu.class);
                            startActivity(intent);
                            break;
                        case R.id.theory_menu:
                            intent = new Intent(com.example.niklasjahning.howtojava.ExerciseForMethods1.this, TheoryMenu.class);
                            startActivity(intent);
                            break;
                        case R.id.setting_menu:
                            intent = new Intent(com.example.niklasjahning.howtojava.ExerciseForMethods1.this, SettingsMenu.class);
                            startActivity(intent);
                            break;
                        case R.id.moveToTheory:
                            finish();
                            intent = new Intent(com.example.niklasjahning.howtojava.ExerciseForMethods1.this, DataTypes.class);
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
                if ( (box1.isChecked()|| box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <=6))
                {
                    checkCorrectAnswers();
                    answered[i] = true;
                    i++;
                    setText();
                }
                else if (i>6)
                {
                    PlayMenu.unlockLevelNumber = 1;
                    finish();
                }
            }
        }

        private void setText()
        {
            switch (i) {
                case 0:
                    textView.setText("Welche Aussage trifft zu?");
                    box1.setText("Methoden manipulieren Daten");
                    box2.setText("Methoden sind Objekte von Klassen");
                    box3.setText("Methoden können keine Werte zurückgeben");
                    box4.setText("Methoden gibt es in Java nicht");
                    break;
                case 1:
                    textView.setText("Was für Werte können von Methoden zurückgegeben werden?");
                    box1.setText("Methoden können keine Werte zurückgeben");
                    box2.setText("nur einfache Datentypen wie int, boolean und Strings");
                    box3.setText("nur Objekte einer Klasse");
                    box4.setText("Methoden können nur den Typ zurückgeben, mit dem sie deklariert wurden");
                    break;
                case 2:
                    textView.setText("Was sind gültige Methodennamen?");
                    box1.setText("public int ZAHLa&BADDIEREN");
                    box2.setText("public int zahlenAddieren");
                    box3.setText("public int 2ZahlenAddieren");
                    box4.setText("public int %addieren%");
                    break;
                case 3:
                    textView.setText("Welche Methodendeklaration ist notwendig um eine Zahl zurückgeben zu können?");
                    box1.setText("public void zahlZurückgeben()");
                    box2.setText("public int zahlzurückgeben()");
                    box3.setText("public double zahlZurückgeben");
                    box4.setText("public boolean zahlZurückgeben()");
                    break;
                case 4:
                    textView.setText("Mit welchem Befehl wird die Methode \"private void doSomething()\" gestartet?");
                    box1.setText("doSomething.start();");
                    box2.setText("operate(doSomething);");
                    box3.setText("doSomething();");
                    box4.setText("private void doSomething();");
                    break;
                case 5:
                    textView.setText("In einer Methode wird ein boolean deklariert. Man versucht diesen über eine andere Methode zu verändern. Ist das möglich? funktionieren?");
                    box1.setText("Ja, es wird funktionieren.");
                    box2.setText("Nein, es klappt nicht, da Methoden keine anderen Methoden aufrufen können.");
                    box3.setText("Nein, es klappt nicht, da eine lokale Variable nur solange existiert wie die Methode ausgeführt wird.");
                    box4.setText("Nein, es klappt nicht, weil boolean in Methoden nicht verwendet werden dürfen.");
                    break;
                case 6:
                    for (int j = 0; j < 6; j++)
                    {
                        if (answerCorrect[j])
                        {numOfCorrectAnswers ++;}
                    }
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


        private void resetCheckbox ()
        {
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
                case 1: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
                {
                    answerCorrect[i] = true;
                }
                    break;
                case 2: if (!box1.isChecked() && box2.isChecked() && !box3.isChecked() && !box4.isChecked())
                {
                    answerCorrect[i] = true;
                }
                    break;
                case 3: if (!box1.isChecked() && box2.isChecked() || box3.isChecked() && !box4.isChecked())
                {
                    answerCorrect[i] = true;
                }
                    break;
                case 4: if (!box1.isChecked() && !box2.isChecked() && box3.isChecked() && !box4.isChecked())
                {
                    answerCorrect[i] = true;
                }
                    break;
                case 5: if (!box1.isChecked() && !box2.isChecked() && box3.isChecked() && !box4.isChecked())
                {
                    answerCorrect[i] = true;
                }
                    break;

                default:
                    break;
            }
        }
    }
