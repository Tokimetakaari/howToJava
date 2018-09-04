package com.example.niklasjahning.howtojava;

import android.view.MotionEvent;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button theory, play, settings;
    private GestureDetectorCompat gestureObject;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

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

        switch (view.getId())
        {
            case R.id.theory_button:
                            i = new Intent(MainActivity.this, TheoryMenu.class);
                            break;
            case R.id.play_button:
                            i = new Intent(MainActivity.this, PlayMenu.class);
                            break;
            case R.id.settings_button:
                            i = new Intent(MainActivity.this,SettingsMenu.class);
                            break;

        }
        startActivity(i);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event2.getX() > event1.getX()) {
                Intent intent2 = new Intent (MainActivity.this,TheoryMenu.class);
                startActivity(intent2);

            }
            else if (event2.getX() < event1.getX()){
                Intent intent1 =  new Intent(MainActivity.this, PlayMenu.class);
                startActivity(intent1);
            }
            return true;
        }
    }
}
