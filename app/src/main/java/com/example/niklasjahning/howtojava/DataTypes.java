package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DataTypes extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SettingsMenu.switchOnOff1) {
            setTheme(R.style.Kai);
        }
        setContentView(R.layout.datatypes_menu);
        setupButtons();
        setupListener();
    }

    private void setupButtons()
    {
        b1 = findViewById(R.id.datatypes_menu_1);
        b2 = findViewById(R.id.datatypes_menu_2);
        b3 = findViewById(R.id.datatypes_menu_3);
        b4 = findViewById(R.id.datatypes_menu_4);
    }

    private void setupListener()
    {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent i = new Intent(DataTypes.this, Theory.class);
        switch (view.getId())
        {
            case R.id.datatypes_menu_1: i.putExtra("txt_num",11);
                break;
            case R.id.datatypes_menu_2: i.putExtra("txt_num", 12);
                break;
            case R.id.datatypes_menu_3: i.putExtra("txt_num",13);
                break;
            case R.id.datatypes_menu_4: i.putExtra("txt_num",14);
                break;
        }
        startActivity(i);
    }
}
