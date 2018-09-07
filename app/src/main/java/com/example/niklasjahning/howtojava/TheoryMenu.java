package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TheoryMenu extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    Intent i ;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SettingsMenu.switchOnOff1) {
            setTheme(R.style.Kai);
        }
        setContentView(R.layout.menu);
        setupDrawer();
        setupButtons();
        setupListener();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.burgerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        connectBurger();

    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:
                        i = new Intent(TheoryMenu.this, PlayMenu.class);
                        startActivity(i);

                    case R.id.theory_menu:
                        Toast.makeText(getApplicationContext(),"Du bist bereits in Theory",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting_menu:
                        i = new Intent(TheoryMenu.this, SettingsMenu.class);
                        startActivity(i);
                    case R.id.moveToTheory:
                        Toast.makeText(getApplicationContext(),"Du bist aktuell in keiner Übung",Toast.LENGTH_SHORT).show();
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),"Thanks for playing!",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
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
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.lektion_1: i = new Intent(TheoryMenu.this, DataTypes.class);
                break;
            case R.id.lektion_2: i = new Intent(TheoryMenu.this, ClassesAndObjects.class);
                break;
            case R.id.lektion_3: i = new Intent(TheoryMenu.this, LogicAndConditions.class);
                break;
            case R.id.lektion_4: i = new Intent(TheoryMenu.this, Arrays.class);
                break;
            case R.id.lektion_5: i = new Intent(TheoryMenu.this, Loops.class);
                break;
            case R.id.lektion_6: i = new Intent(TheoryMenu.this, Theory.class);
                                    i.putExtra("txt_num", 61);
                break;
            case R.id.lektion_7: i = new Intent(TheoryMenu.this, Methods.class);
                break;
            case R.id.lektion_8: i = new Intent(TheoryMenu.this, Theory.class);
                                    i.putExtra("txt_num", 81);
                break;
            case R.id.lektion_9: i = new Intent(TheoryMenu.this, InterfacesAndEvents.class);
                break;
            case R.id.lektion_10: i = new Intent(TheoryMenu.this, DataInJava.class);
                break;

        }
        startActivity(i);
    }
}
