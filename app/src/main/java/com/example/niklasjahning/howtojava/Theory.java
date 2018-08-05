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
            case 1: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 2: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 3: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 4: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 5: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 6: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 7: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 8: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 9: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
            case 10: is = this.getResources().openRawResource(R.raw.Lektion1);
                break;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if(is!=null)
        {while ((string = reader.readLine()) != null)
        {buf.append(string+ "/n");}
        }
        is.close();
        textView.setText(buf.toString());
    }

    @Override
    public void onClick(View view) {

    }
}
