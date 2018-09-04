package com.example.niklasjahning.howtojava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciseSelectDatatypes2 extends AppCompatActivity implements View.OnClickListener {


    TextView textView;
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8;
    Button submit;
    int i = 0;
    int numOfCorrectAnswers = 0;
    String solution1 = "int age = 4;";
    String alternative1_1 = "int age= 4;";
    String alternative1_2 = "int age=4;";
    String alternative1_3 ="int age =4;";
    String solution2 = "boolean lazy = true;";
    String alternative2_1 = "boolean lazy= true;";
    String alternative2_2 = "boolean lazy =true;";
    String alternative2_3 ="boolean lazy=true;";
    String solution3 = "String name;";

    boolean[] answerCorrect = new boolean[3];
    boolean[] answered = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloze_default);
        setupItems();
        setText();


    }

    private void setText() {

        switch (i) {
            case 0:
                textView.setText("Erstelle einen Integer mit dem Namen 'age' und dem Wert 4. ");
                editText2.setVisibility(View.GONE);
                editText3.setVisibility(View.GONE);
                editText4.setVisibility(View.GONE);
                editText5.setVisibility(View.GONE);
                editText6.setVisibility(View.GONE);
                editText7.setVisibility(View.GONE);
                editText8.setVisibility(View.GONE);

                break;
            case 1:
                textView.setText("Erstellen Sie einen Boolean mit dem Namen 'lazy' und den Wert 'true':");
                break;
            case 2:
                textView.setText("Legen Sie einen String fest mit den Namen 'name' fest:");
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
                PlayMenu.positionOfNewLevel = 2;
                finish();

            }
        }
    }

    private void checkCorrectAnswers() {

//        Falls mehrere Schreibweise zählen einfach eine weitere Bedingung mit || hinzufügen editText1.getText().toString().equals(alternative1)

        switch (i) {
            case 0:
                if (editText1.getText().toString().equals(solution1) || (editText1.getText().toString().equals(alternative1_1)) || (editText1.getText().toString().equals(alternative1_2)) || (editText1.getText().toString().equals(alternative1_3))) {
                    answerCorrect[i] = true;
                }
            case 1:
                if (editText1.getText().toString().equals(solution2) || (editText1.getText().toString().equals(alternative2_1)) || (editText1.getText().toString().equals(alternative2_2)) || (editText1.getText().toString().equals(alternative2_3))) {
                    answerCorrect[i] = true;
                }
                break;
            case 2:
                if (editText1.getText().toString().equals(solution3)) {
                    answerCorrect[i] = true;

                }


        }

    }

    private void resetEdit()
    {
        editText1.setText("");
    }
}