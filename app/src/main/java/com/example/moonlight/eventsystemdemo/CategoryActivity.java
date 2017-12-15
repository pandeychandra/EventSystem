/*
package com.example.moonlight.eventsystemdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    TextView cost1, cost2, cost3;
    String c1, c2, c3;
    Button cat1, cat2, cat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        cat1 = (Button) findViewById(R.id.firstC);
        cat1.setOnClickListener(this);
        cat2 = (Button) findViewById(R.id.secondC);
        cat2.setOnClickListener(this);
        cat3 = (Button) findViewById(R.id.thirdC);
        cat3.setOnClickListener(this);

        cost1 = (TextView) findViewById(R.id.cat1prize);
        */
/*cost2 = (TextView) findViewById(R.id.cat2prize);
        cost3 = (TextView) findViewById(R.id.cat3prize);*//*

        c1 = getIntent().getStringExtra("cost1");
        c2 = getIntent().getStringExtra("cost2");
        c3 = getIntent().getStringExtra("cost3");
        cost1.setText("---------" + "$" + c1 + ".00");
        cost2.setText("---------" + "$" + c2 + ".00");
        cost3.setText("---------" + "$" + c3 + ".00");
       */
/* cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(CategoryActivity.this,Booking1.class);
                myintent.putExtra("costs1", c1);
                startActivity(myintent);


            }
        });
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(CategoryActivity.this,Booking1.class);
                myintent.putExtra("costs2", c2);
                startActivity(myintent);


            }
        });
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(CategoryActivity.this,Booking1.class);
                myintent.putExtra("costs3", c3);
                startActivity(myintent);



            }
                    });
*//*

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.firstC:
                Intent myintent = new Intent(CategoryActivity.this, Booking1.class);
                myintent.putExtra("costs1", c1);
                startActivity(myintent);
                break;
            case R.id.secondC:
                Intent myinten = new Intent(CategoryActivity.this, Booking1.class);
                myinten.putExtra("costs1", c2);
                startActivity(myinten);

                break;
            case R.id.thirdC:
                Intent myinte = new Intent(CategoryActivity.this, Booking1.class);
                myinte.putExtra("costs1", c3);
                startActivity(myinte);
                break;

        }
    }
}
*/
