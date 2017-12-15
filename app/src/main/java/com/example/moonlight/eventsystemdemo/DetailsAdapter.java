package com.example.moonlight.eventsystemdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by MoonLight on 11/13/2017.
 */

public class DetailsAdapter  extends RecyclerView.Adapter<DetailsAdapter.CustomViewHolder>  {
    String nam[];
    String name, locD, date, phone, lat_long, cos, token;
    Bitmap bitmap;
    ArrayList<DataSave> arrayList;
    Context mContext;
    String n, p;
    //int  MY_PERMISSIONS_REQUEST_CALL_PHONE=1;
    String EvenName;
    DataSave dataSave;
    FirebaseAuth mauth;
    FirebaseUser mUser;


    public DetailsAdapter(Context c, ArrayList<DataSave> list1) {
        this.arrayList = list1;
        this.mContext = c;

    }

    public ViewGroup group;
    public int ii;
    CustomViewHolder viewHolder;
    View view;
    @Override
    public DetailsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_download, null);
        viewHolder = new DetailsAdapter.CustomViewHolder(view);


        return viewHolder;
    }

   /* @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext,LoginActivity.class);

        mContext.startActivity(intent);
    }*/

    @Override
    public void onBindViewHolder(DetailsAdapter.CustomViewHolder customViewHolder, final int i) {
        dataSave = (DataSave) arrayList.get(i);
        EvenName = dataSave.getEventName();
        locD=dataSave.getEventLocation();
        customViewHolder.name.setText(EvenName);
        customViewHolder.loc.setText(dataSave.getEventLocation());
        customViewHolder.date.setText(dataSave.getEventdate());
        String imge = dataSave.getEventImage();
        phone = dataSave.getPhone();
        //customViewHolder.setOnClickListener(this);



        String ip=ipconfig.ip;
        /*customViewHolder.cost.setText("$"+dataSave.getPrice1());*/
        Picasso.with(mContext).load("http://"+ip+"/event/uploads/" + imge + ".jpeg").into(customViewHolder.im);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Tool", MODE_PRIVATE);
        n = sharedPreferences.getString("name", "");
        p = sharedPreferences.getString("password", "");


        customViewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (n != "" && p != "") {
                    String token=dataSave.getToken();
                   /* cos=dataSave.getPrice1();*/
                    Intent myintent=new Intent(mContext, Booking1.class);
                   /* myintent.putExtra("cost", cos);*/
                    myintent.putExtra("eName",EvenName);
                    myintent.putExtra("t",token);
                    mContext.startActivity(myintent);

                } else {
                    Intent i = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(i);



                }
            }
        });


        customViewHolder.call.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone, null));
                mContext.startActivity(intent);


            }
        });
        customViewHolder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latlong=lat_long;
                String uriBegin = "geo:" + latlong;
                String query = latlong;
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);


                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + locD);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mContext. startActivity(mapIntent);

            }
        });



    }






    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        TextView loc;
        TextView date, cost;
        ImageView im;
        TextView book;
        Button phn, map;
        ImageButton call, location;
        private CardView r;
        RelativeLayout rrl;

        public CustomViewHolder(View view) {

            super(view);
            name = (TextView) view.findViewById(R.id.nam);
            loc = (TextView) view.findViewById(R.id.location);
            date = (TextView) view.findViewById(R.id.date);
            im = (ImageView) view.findViewById(R.id.image);
            book = (TextView) view.findViewById(R.id.book);
            //phn=(Button)view.findViewById(R.id.phone);
            call = (ImageButton) view.findViewById(R.id.imageCall);
            location = (ImageButton) view.findViewById(R.id.imageLoc);
            //map=(Button)view.findViewById(R.id.map);
            cost = (TextView) view.findViewById(R.id.cost);
        }
    }
}

