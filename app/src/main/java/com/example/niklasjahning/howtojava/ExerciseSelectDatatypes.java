package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExerciseSelectDatatypes extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    CheckBox box1, box2, box3, box4;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers=0;

    boolean[] answerCorrect = new boolean[5];
    boolean[] answered = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            if ( (box1.isChecked()|| box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <=5))
            {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();

            } else if (i>=5) {
               finish();

        }
    }
    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText("Welchen Datentyp würden Sie verwenden um den Wert 5 zu speichern?");
                box1.setText("int");
                box2.setText("float");
                box3.setText("String");
                box4.setText("boolean");
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
                for (int j = 0; j < 5; j++)
                {
                    if (answerCorrect[j])
                    {numOfCorrectAnswers ++;}
                }
                textView.setText("Sie haben " + numOfCorrectAnswers +" richtig beantwortet");
                box1.setVisibility(View.INVISIBLE);
                box1.setClickable(false);
                box2.setVisibility(View.INVISIBLE);
                box2.setClickable(false);
                box3.setVisibility(View.INVISIBLE);
                box3.setClickable(false);
                box4.setVisibility(View.INVISIBLE);
                box4.setClickable(false);
                i++;
                break;

                default:
                    nextLevel();
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
            case 0: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked())
                    {
                        answerCorrect[i] = true;
                    }
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

    private void nextLevel()
    {
        if (i >= 5)
        {

            Intent intent = new Intent( this, PlayMenu.class);
            startActivity(intent);
            finish();
        }
    }
}