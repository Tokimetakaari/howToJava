package com.example.niklasjahning.howtojava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExerciseOperators extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    CheckBox box1, box2, box3, box4;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers=0;

    boolean[] answerCorrect = new boolean[7];
    boolean[] answered = new boolean[7];

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
            if ( (box1.isChecked()|| box2.isChecked() || box3.isChecked() || box4.isChecked()) && (i <=7))
            {
                checkCorrectAnswers();
                answered[i] = true;
                i++;
                setText();

            } else if (i>=7) {
                finish();

            }
        }
    }

    private void setText() {
        switch (i) {
            case 0:
                textView.setText("Der Anfangswert von x beträgt 7. Sie inkrementieren drei Mal. Welchen Wert hat x jetzt?");
                box1.setText("4");
                box2.setText("10");
                box3.setText("8");
                box4.setText("5");
                break;
            case 1:
                textView.setText("Der Anfangswert von x beträgt 24. Sie dekrementieren fünfmal. Welchen Wert hat x jetzt?");
                box1.setText("10");
                box2.setText("29");
                box3.setText("20");
                box4.setText("19");
                break;
            case 2:
                textView.setText("Lösen sie folgende Gleichung: 20 % 7 = ?");
                box1.setText("6");
                box2.setText("3");
                box3.setText("2");
                box4.setText("7");
                break;
            case 3:
                textView.setText("Lösen sie folgende Gleichung: 18 % 6 = ?");
                box1.setText("3");
                box2.setText("6");
                box3.setText("18");
                box4.setText("0");
                break;
            case 4:
                textView.setText("Es sind folgende Werte gegeben: x = 5 und y = 7. Sie inkrementieren x zweimal und zeitgleich dekrementieren Sie dreimal y. Lösen sie folgenden Term: x - y = ?");
                box1.setText("11");
                box2.setText("3");
                box3.setText("-7");
                box4.setText("-3");
                break;


            case 5:
                textView.setText("Es sind folgende Werte gegeben: x = 28 und y = 14. Sie dekrementieren x sechsmal und zeitgleich dekrementieren Sie zweimal y. Lösen sie folgenden Term: x % y = ?");
                box1.setText("11");
                box2.setText("14");
                box3.setText("18");
                box4.setText("10");
                break;

            case 6:
                textView.setText("Welche Gleichung ergibt 5 ? (Mehrfachantworten möglich)");
                box1.setText("50 % 15 = ?");
                box2.setText("18 % 5 = ?");
                box3.setText("13 % 10 = ?");
                box4.setText("70 % 65 = ?");
                break;
            case 7:
                for (int j = 0; j < 7; j++)
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
            case 0: if (!box1.isChecked() && box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
            case 1: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 2: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 3: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;
            case 4: if (!box1.isChecked() && box2.isChecked() && !box3.isChecked() && !box4.isChecked())
            {
                answerCorrect[i] = true;
            }
            case 5: if (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
            case 6: if (box1.isChecked() && !box2.isChecked() && !box3.isChecked() && box4.isChecked())
            {
                answerCorrect[i] = true;
            }
                break;

            default:

                break;
        }
    }






}
