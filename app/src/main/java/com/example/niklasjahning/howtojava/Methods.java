package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Methods extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SettingsMenu.switchOnOff1) {
            setTheme(R.style.Kai);
        }
        setContentView(R.layout.methods_menu);
        setupButtons();
        setupListener();
    }

    private void setupButtons()
    {
        b1 = findViewById(R.id.methods_menu_1);
        b2 = findViewById(R.id.methods_menu_2);
        b3 = findViewById(R.id.methods_menu_3);

    }

    private void setupListener()
    {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        Intent i = new Intent(Methods.this, Theory.class);
        switch (view.getId())
        {
            case R.id.methods_menu_1: i.putExtra("txt_num",71);
                break;
            case R.id.methods_menu_2: i.putExtra("txt_num", 72);
                break;
            case R.id.methods_menu_3: i.putExtra("txt_num",73);
                break;

        }
        startActivity(i);
    }
}
