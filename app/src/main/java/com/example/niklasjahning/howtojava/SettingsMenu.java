package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SettingsMenu extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView burger;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        setupDrawer();
        connectBurger();
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
                        Toast.makeText(getApplicationContext(),"Du befindest dich bereits in den Einstellungen",Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    case R.id.credits:
                        Toast.makeText(getApplicationContext(),"Thanks for playing!",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
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

    @Override
    public void onClick(View view) {

    }
}
