package com.example.moonlight.eventsystemdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by MoonLight on 11/2/2017.
 */

public class PayPostClass extends AppCompatActivity{
    TextView t1,t2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_download);
        t1=(TextView)findViewById(R.id.nam);
    }
}
