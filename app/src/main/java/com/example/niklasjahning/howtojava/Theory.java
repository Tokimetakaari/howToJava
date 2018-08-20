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
import java.io.StringWriter;

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
            case 11: is = this.getResources().openRawResource(R.raw.lektion_1_1);
                break;
            case 12: is = this.getResources().openRawResource(R.raw.lektion_1_2);
                break;
            case 13: is = this.getResources().openRawResource(R.raw.lektion_1_3);
                break;
            case 14: is = this.getResources().openRawResource(R.raw.lektion_1_4);
                break;
            case 21: is = this.getResources().openRawResource(R.raw.lektion_2_1);
                break;
            case 22: is = this.getResources().openRawResource(R.raw.lektion_2_2);
                break;





            case 41: is = this.getResources().openRawResource(R.raw.lektion_4_1);
                break;
            case 42: is = this.getResources().openRawResource(R.raw.lektion_4_2);
                break;
            case 43: is = this.getResources().openRawResource(R.raw.lektion_4_3);
                break;
            case 44: is = this.getResources().openRawResource(R.raw.lektion_4_4);
                break;
            case 45: is = this.getResources().openRawResource(R.raw.lektion_4_5);
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
