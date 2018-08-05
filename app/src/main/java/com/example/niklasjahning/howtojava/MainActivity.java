package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button theory, play, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupButtons();
        setupListener();
    }

    private void setupButtons()
    {
        theory = findViewById(R.id.theory_button);
        play = findViewById(R.id.play_button);
        settings = findViewById(R.id.settings_button);
    }

    private void setupListener()
    {
        theory.setOnClickListener(this);
        play.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent i;
        switch (view.getId())
        {
            case R.id.theory_button:
                            i = new Intent(this, TheoryMenu.class);
                            startActivity(i);
                            break;
            case R.id.play_button:
                            i = new Intent(this, PlayMenu.class);
                            startActivity(i);
                            break;
            case R.id.settings_button:
                            i = new Intent(this,SettingsMenu.class);
                            startActivity(i);
                            break;
        }
    }
}
