package com.example.niklasjahning.howtojava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExerciseClassesAndObjects2 extends AppCompatActivity implements View.OnClickListener {


    TextView textView;
    CheckBox box1, box2, box3, box4;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers=0;

    boolean[] answerCorrect = new boolean[5];
    boolean[] answered = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox_layout_default);
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
                PlayMenu.positionOfNewLevel = 4;
                finish();
            }
        }
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
                for (int j = 0; j < 3; j++)
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


}
