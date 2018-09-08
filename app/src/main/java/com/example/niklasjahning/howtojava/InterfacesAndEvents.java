package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class InterfacesAndEvents extends AppCompatActivity implements View.OnClickListener  {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SettingsMenu.switchOnOff1) {
            setTheme(R.style.Kai);
        }
        setContentView(R.layout.interfaces_events_menu);
        setupButtons();
        setupListener();
    }

    private void setupButtons() {
        b1 = findViewById(R.id.interfaces_menu1);
        b2 = findViewById(R.id.interfaces_menu2);
    }

    private void setupListener() {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        Intent i = new Intent(InterfacesAndEvents.this, Theory.class);
        switch (view.getId())
        {
            case R.id.interfaces_menu1: i.putExtra("txt_num",91);
                break;
            case R.id.interfaces_menu2: i.putExtra("txt_num", 92);
                break;
        }
        startActivity(i);
    }
}
