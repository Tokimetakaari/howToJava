package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TheoryMenu extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        setupButtons();
        setupListener();
    }

    private void setupButtons()
    {
        b1 = findViewById(R.id.lektion_1);
        b2 = findViewById(R.id.lektion_2);
        b3 = findViewById(R.id.lektion_3);
        b4 = findViewById(R.id.lektion_4);
        b5 = findViewById(R.id.lektion_5);
        b6 = findViewById(R.id.lektion_6);
        b7 = findViewById(R.id.lektion_7);
        b8 = findViewById(R.id.lektion_8);
        b9 = findViewById(R.id.lektion_9);
        b10 = findViewById(R.id.lektion_10);
        b11 = findViewById(R.id.lektion_11);
    }

    private void setupListener()
    {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent i = new Intent(TheoryMenu.this, Theory.class);

        switch (view.getId())
        {
            case R.id.lektion_1: i.putExtra("txt_num",1);
                break;
            case R.id.lektion_2: i.putExtra("txt_num",2);
                break;
            case R.id.lektion_3: i.putExtra("txt_num",3);
                break;
            case R.id.lektion_4: i.putExtra("txt_num",4);
                break;
            case R.id.lektion_5: i.putExtra("txt_num",5);
                break;
            case R.id.lektion_6: i.putExtra("txt_num",6);
                break;
            case R.id.lektion_7: i.putExtra("txt_num",7);
                break;
            case R.id.lektion_8: i.putExtra("txt_num",8);
                break;
            case R.id.lektion_9: i.putExtra("txt_num",9);
                break;
            case R.id.lektion_10: i.putExtra("txt_num",10);
                break;
            case R.id.lektion_11: i.putExtra("txt_num",11);
                break;
        }
        startActivity(i);
    }
}
