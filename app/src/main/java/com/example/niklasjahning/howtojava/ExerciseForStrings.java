package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciseForStrings extends AppCompatActivity implements View.OnClickListener {

    TextView view1, view2;
    EditText text1, text2;
    Button submit;
    int numOfCorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_exercise);
        Intent i = getIntent();
        Bundle data = i.getExtras();
        numOfCorrectAnswers = data.getInt("correctAnswers");
        initViews();
        setText();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.programmingExerciseButton)
        {

        }
    }

    private void initViews()
    {
        view1 = findViewById(R.id.programmingExerciseTextView_1);
        view2 = findViewById(R.id.programmingExerciseTextView_2);
        text1 = findViewById(R.id.programmingExerciseEditText_1);
        text2 = findViewById(R.id.programmingExerciseEditText_2);
        submit = findViewById(R.id.programmingExerciseButton);
    }

    private void setText()
    {

    }
}
