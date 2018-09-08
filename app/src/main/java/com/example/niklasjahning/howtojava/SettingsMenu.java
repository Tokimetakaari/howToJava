package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsMenu extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent i;
    private Switch aSwitch1, aSwitch2, aSwitch3, aSwitch4;
    Button saveButton;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "swtich1";
    public static final String SWITCH2 = "swtich2";
    public static final String SWITCH3 = "swtich3";
    public static final String SWITCH4 = "swtich4";
    public static boolean switchOnOff1;
    public static boolean switchOnOff2;
    public static boolean switchOnOff3;
    public static boolean switchOnOff4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPref();
        if(switchOnOff1) {
            setTheme(R.style.Kai);
        }
        if(!switchOnOff1){
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.settings);
        setupItems();
        setupDrawer();
        connectBurger();


    }


    private void setupItems() {
        aSwitch1 = findViewById(R.id.color1_switch);
        aSwitch2 = findViewById(R.id.color2_switch);
        aSwitch3 = findViewById(R.id.color3_switch);
        aSwitch4 = findViewById(R.id.color4_switch);
        saveButton = findViewById(R.id.setting_save_button);
        saveButton.setOnClickListener(this);
    }

    private void connectBurger() {
        burger = findViewById(R.id.test);
        burger.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.play_menu:

                        i = new Intent(SettingsMenu.this, PlayMenu.class);
                        startActivity(i);

                    case R.id.theory_menu:
                        i = new Intent(SettingsMenu.this, TheoryMenu.class);
                        startActivity(i);
                        break;
                    case R.id.setting_menu:
                        Toast.makeText(getApplicationContext(), "Du befindest dich bereits in den Einstellungen", Toast.LENGTH_SHORT).show();
                    case R.id.moveToTheory:
                        Toast.makeText(getApplicationContext(), "Du bist aktuell in keiner Übung", Toast.LENGTH_SHORT).show();
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(), "Thanks for playing!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setupDrawer() {
        mDrawerLayout = findViewById(R.id.burgerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.setting_save_button:
                savePref();
                break;
        }
        loadPref();
        updateIt();

    }

    public void savePref() {
        SharedPreferences sharedPreferences =getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(SWITCH1, aSwitch1.isChecked());
        editor.putBoolean(SWITCH2, aSwitch2.isChecked());
        editor.putBoolean(SWITCH3, aSwitch3.isChecked());
        editor.putBoolean(SWITCH4, aSwitch4.isChecked());

        editor.apply();

        Toast.makeText(this, "Neustart pls", Toast.LENGTH_LONG).show();

    }

    public void loadPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff1 = sharedPreferences.getBoolean(SWITCH1,false);
        switchOnOff2 = sharedPreferences.getBoolean(SWITCH2,false);
        switchOnOff3 = sharedPreferences.getBoolean(SWITCH3,false);
        switchOnOff4 = sharedPreferences.getBoolean(SWITCH4,false);


    }

    public void updateIt() {
        aSwitch1.setChecked(switchOnOff1);
        aSwitch2.setChecked(switchOnOff2);
        aSwitch3.setChecked(switchOnOff3);
        aSwitch4.setChecked(switchOnOff4);

    }

}
