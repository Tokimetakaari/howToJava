package com.example.niklasjahning.howtojava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Theory extends AppCompatActivity implements View.OnClickListener
{
    int res;
    TextView textView;
    InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_theory);
        Intent i = getIntent();
        Bundle data = i.getExtras();
        assert data != null;
        res = data.getInt("txt_num");
        try {
            setText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setText() throws IOException {
        textView = findViewById(R.id.textview);
        String string = "";
        StringBuffer buf = new StringBuffer();
        switch (res)
        {
            case 11: is = this.getResources().openRawResource(R.raw.lektion1_1);
                break;
            case 12: is = this.getResources().openRawResource(R.raw.lektion1_2);
                break;
            case 13: is = this.getResources().openRawResource(R.raw.lektion1_3);
                break;
            case 14: is = this.getResources().openRawResource(R.raw.lektion1_4);
                break;
            case 21: is = this.getResources().openRawResource(R.raw.lektion2_1);
                break;
            case 22: is = this.getResources().openRawResource(R.raw.lektion2_2);
                break;
            case 31: is = this.getResources().openRawResource(R.raw.lektion3_1);
                break;
            case 32: is = this.getResources().openRawResource(R.raw.lektion3_2);
                break;
            case 33: is = this.getResources().openRawResource(R.raw.lektion3_3);
                break;
            case 34: is = this.getResources().openRawResource(R.raw.lektion3_4);
                break;
            case 35: is = this.getResources().openRawResource(R.raw.lektion3_5);
                break;
            case 36: is = this.getResources().openRawResource(R.raw.lektion3_6);
                break;
            case 41: is = this.getResources().openRawResource(R.raw.lektion4_1);
                break;
            case 42: is = this.getResources().openRawResource(R.raw.lektion4_2);
                break;
            case 43: is = this.getResources().openRawResource(R.raw.lektion4_3);
                break;
            case 44: is = this.getResources().openRawResource(R.raw.lektion4_4);
                break;
            case 45: is = this.getResources().openRawResource(R.raw.lektion4_5);
                break;
            case 51: is = this.getResources().openRawResource(R.raw.lektion5_1);
                break;
            case 52: is = this.getResources().openRawResource(R.raw.lektion5_2);
                break;
            case 61: is = this.getResources().openRawResource(R.raw.lektion6_1);
                break;
            case 71: is = this.getResources().openRawResource(R.raw.lektion7_1);
                break;
            case 72: is = this.getResources().openRawResource(R.raw.lektion7_2);
                break;
            case 73: is = this.getResources().openRawResource(R.raw.lektion7_3);
                break;
            case 81: is = this.getResources().openRawResource(R.raw.lektion8_1);
                break;
            case 91: is = this.getResources().openRawResource(R.raw.lektion9_1);
                break;
            case 92: is = this.getResources().openRawResource(R.raw.lektion9_2);
                break;
            case 101: is = this.getResources().openRawResource(R.raw.lektion10_1);
                break;
            case 102: is = this.getResources().openRawResource(R.raw.lektion10_2);
                break;
            case 103: is = this.getResources().openRawResource(R.raw.lektion10_3);
                break;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if(is!=null)
        {while ((string = reader.readLine()) != null)
        {buf.append(string+ "\n");}
        }
        is.close();

     textView.setText(buf.toString());
    }

    @Override
    public void onClick(View view) {
    }
}
