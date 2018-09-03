package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Constuctors extends AppCompatActivity
{

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constructors_menu);
        setupButtons();
    }

    private void setupButtons()
    {
        b1 = findViewById(R.id.constuctors_menu_1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Constuctors.this, Theory.class);
                i.putExtra("txt_num", 61);
                startActivity(i);
            }
        });
    }
}

