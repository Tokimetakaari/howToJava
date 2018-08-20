package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Arrays extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3, b4, b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.array_menu);
        setupButtons();
        setupListener();
    }

    private void setupButtons()
    {
        b1 = findViewById(R.id.arrays_menu_1);
        b2 = findViewById(R.id.arrays_menu_2);
        b3 = findViewById(R.id.arrays_menu_3);
        b4 = findViewById(R.id.arrays_menu_4);
        b5 = findViewById(R.id.arrays_menu_5);
    }

    private void setupListener()
    {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        Intent i = new Intent(Arrays.this, Theory.class);
        switch (view.getId())
        {
            case R.id.arrays_menu_1: i.putExtra("txt_num",41);
                break;
            case R.id.arrays_menu_2: i.putExtra("txt_num", 42);
                break;
            case R.id.arrays_menu_3: i.putExtra("txt_num",43);
                break;
            case R.id.arrays_menu_4: i.putExtra("txt_num",44);
                break;
            case R.id.arrays_menu_5: i.putExtra("txt_num", 45);
        }
        startActivity(i);
    }
}
