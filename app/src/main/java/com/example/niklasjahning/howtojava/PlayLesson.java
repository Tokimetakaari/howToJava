package com.example.niklasjahning.howtojava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PlayLesson extends AppCompatActivity implements View.OnClickListener {

    int layoutNumber;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Bundle data = i.getExtras();
        layoutNumber = data.getInt("layout_num");
        selectLayout();
    }

    private void selectLayout()
    {
     /*   switch (layoutNumber)
        {
            case 1:  setContentView();
            break;
            case 2: setContentView();
            break;
        }*/

    }

    @Override
    public void onClick(View view) {

    }
}
