package com.example.moonlight.eventsystemdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    TextView name,date,desc,money,location,time,book;
    ImageView imageView;
    String ip=ipconfig.ip;
    ImageButton call, loc;
    String n,p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name=(TextView)findViewById(R.id.nam);
        time=(TextView)findViewById(R.id.time);
        date=(TextView)findViewById(R.id.date);
        desc=(TextView)findViewById(R.id.desc);
        call = (ImageButton)findViewById(R.id.imageCall);
        loc = (ImageButton)findViewById(R.id.imageLoc);
        money=(TextView)findViewById(R.id.cost);
        location=(TextView)findViewById(R.id.location);
        imageView=(ImageView) findViewById(R.id.im);
        Bundle bundle = getIntent().getExtras();
        final String[] object = bundle.getStringArray("message");
        book=(TextView)findViewById(R.id.book);
        SharedPreferences sharedPreferences = this.getSharedPreferences("Tool", MODE_PRIVATE);
        n = sharedPreferences.getString("name", "");
        p = sharedPreferences.getString("password", "");


        //TODO retrieve1.title,retrieve1.article,retrieve1.name,retrieve1.option,retrieve1.date,retrieve1.imgUrl
        name.setText(object[0]);
        time.setText("At"+object[6]);




       /* money.setText("$"+object[1]);*/
        location.setText(object[2]);
        date.setText(object[3]);
        desc.setText("1"+object[4]);
        try {
                Picasso.with(DetailsActivity.this).load("http://" + ip + "/event/uploads/" + object[1] + ".jpeg").into(imageView);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String lat_long;
                String latlong=lat_long;

                String uriBegin = "geo:" + latlong;
                String query = latlong;
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent)*/;


                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + object[3]);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
        call.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", object[5], null));
                startActivity(intent);

            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n != "" && p != "") {
                   //String token=dataSave.getToken();
                    String m=object[1];
                    Intent myintent=new Intent(DetailsActivity.this, DynamicCatButton.class);
                    myintent.putExtra("cost",m);
                    myintent.putExtra("eName",object[0]);
                    myintent.putExtra("cat",object[7]);
                    myintent.putExtra("catprice",object[8]);

                    myintent.putExtra("name",p);
                    //myintent.putExtra("t",token);*/

                  startActivity(myintent);

                } else {
                    Intent i = new Intent(DetailsActivity.this, LoginActivity.class);
                   startActivity(i);



                }

            }
        });

    }
}
