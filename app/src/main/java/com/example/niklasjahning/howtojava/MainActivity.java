package com.example.niklasjahning.howtojava;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;

import static com.example.niklasjahning.howtojava.SettingsMenu.SHARED_PREFS;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button theory, play, settings;
    private GestureDetectorCompat gestureObject;
    private Intent i;
    static public AppDatabase database;
    static final String databaseName = "AppDatabase";
    public static final String SWITCH1 = "swtich1";
    public static final String SWITCH2 = "swtich2";
    public static final String SWITCH3 = "swtich3";
    public static final String SWITCH4 = "swtich4";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SettingsMenu.switchOnOff1) {
            setTheme(R.style.Kai);
        }
        setContentView(R.layout.activity_main);
        setupDataBase();
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
        setupButtons();
        setupListener();
    }

    private void setupDataBase()
    {
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, databaseName).fallbackToDestructiveMigration().build();
        setupDataEntries();
    }
    private void setupDataEntries()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BooleanField booleanField = new BooleanField();
                booleanField.setValue(1);
                booleanField.setFieldPosition(1);
                database.booleanDao().insertBooleanField(booleanField);

                for (int j = 2; j < PlayMenu.numOfButtons; j++)
        {
            booleanField = new BooleanField();
            booleanField.setValue(0);
            booleanField.setFieldPosition(j);
            database.booleanDao().insertBooleanField(booleanField);

        }
            }
        }).start();

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
