package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LogicAndConditions extends AppCompatActivity implements View.OnClickListener {

            Button b1, b2, b3, b4, b5, b6;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                if(SettingsMenu.switchOnOff1) {
                    setTheme(R.style.Kai);
                }
                setContentView(R.layout.logische_ausdruecke_menu);
                setupButtons();
                setupListener();
            }

            private void setupButtons()
            {
                b1 = findViewById(R.id.logische_ausdruecke_menu_1);
                b2 = findViewById(R.id.logische_ausdruecke_menu_2);
                b3 = findViewById(R.id.logische_ausdruecke_menu_3);
                b4 = findViewById(R.id.logische_ausdruecke_menu_4);
                b5 = findViewById(R.id.logische_ausdruecke_menu_5);
                b6 = findViewById(R.id.logische_ausdruecke_menu_6);
            }

            private void setupListener()
            {
                b1.setOnClickListener(this);
                b2.setOnClickListener(this);
                b3.setOnClickListener(this);
                b4.setOnClickListener(this);
                b5.setOnClickListener(this);
                b6.setOnClickListener(this);
            }



            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogicAndConditions.this, Theory.class);
                switch (view.getId())
                {
                    case R.id.logische_ausdruecke_menu_1: i.putExtra("txt_num",31);
                        break;
                    case R.id.logische_ausdruecke_menu_2: i.putExtra("txt_num", 32);
                        break;
                    case R.id.logische_ausdruecke_menu_3: i.putExtra("txt_num",33);
                        break;
                    case R.id.logische_ausdruecke_menu_4: i.putExtra("txt_num",34);
                        break;
                    case R.id.logische_ausdruecke_menu_5: i.putExtra("txt_num", 35);
                        break;
                    case R.id.logische_ausdruecke_menu_6: i.putExtra("txt_num", 36);
                        break;
                }
                startActivity(i);
            }
        }



