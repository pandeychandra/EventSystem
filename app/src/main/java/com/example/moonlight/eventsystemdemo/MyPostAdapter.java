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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by MoonLight on 12/13/2017.
 */

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.CustomViewHolder>  {
    public  static MyPostAdapter myPostAdapter;
    String nam[];
    String name, locD, date, phone, lat_long, cos1,cos2,cos3, token,description;
    Bitmap bitmap;
    ArrayList<DataSave> arrayList;
    Context mContext;
    String n, p;

    String EvenName;
    DataSave dataSave;
    FirebaseAuth mauth;
    FirebaseUser mUser;
    String category,id;


    public MyPostAdapter(Context c, ArrayList<DataSave> list1) {
        this.arrayList = list1;
        this.mContext = c;

    }

    /*public ViewGroup group;
    public int ii;*/
    CustomViewHolder viewHolder;
    View view;
    @Override
    public MyPostAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        try {

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_post, null);
            viewHolder = new MyPostAdapter.CustomViewHolder(view);

        }catch ( Exception e)
        {
            e.printStackTrace();
        }
        return viewHolder;
    }

   /* @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext,LoginActivity.class);

        mContext.startActivity(intent);
    }*/

    @Override
    public void onBindViewHolder(MyPostAdapter.CustomViewHolder customViewHolder, final int i) {
        dataSave = (DataSave) arrayList.get(i);
        EvenName = dataSave.getEventName();
        locD=dataSave.getEventLocation();
        customViewHolder.name.setText(EvenName);
        customViewHolder.loc.setText(dataSave.getEventLocation());
        customViewHolder.date.setText(dataSave.getEventdate());
        String imge = dataSave.getEventImage();
        //customViewHolder.time.setText(dataSave.getTime());
        String time=dataSave.getTime();
        customViewHolder.time.setText(time);
        phone = dataSave.getPhone();
//        id=dataSave.getId();
        description=dataSave.getDescription();
        //customViewHolder.setOnClickListener(this);
        ;




        String ip=ipconfig.ip;
        //customViewHolder.cost.setText("$"+dataSave.getPrice1());
        Picasso.with(mContext).load("http://"+ip+"/event/uploads/" + imge + ".jpeg").into(customViewHolder.im);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Tool", MODE_PRIVATE);
        n = sharedPreferences.getString("name", "");
        p = sharedPreferences.getString("password", "");


        customViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new DeleteData(arrayList.get(i).getId()).execute();
                Intent i=new Intent(mContext,MyPostActivity.class);
                   mContext.startActivity(i);


            }
        });
        customViewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(mContext.getApplicationContext(),UpdateActivity.class);

                String[] bodyParts={arrayList.get(i).getEventName(),

                        arrayList.get(i).getEventImage(),
                        arrayList.get(i).getEventLocation(),
                        arrayList.get(i).getEventdate(),
                        arrayList.get(i).getDescription(),
                        arrayList.get(i).getPhone(),
                        arrayList.get(i).getTime(),
                        arrayList.get(i).getCat(),
                        arrayList.get(i).getCategoryprice(),
                          arrayList.get(i).getId()
                        , arrayList.get(i).getToken()
                };

                intent.putExtra("message",bodyParts);
                mContext.startActivity(intent);

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
        customViewHolder.r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(mContext.getApplicationContext(),DetailsActivity.class);

                String[] bodyParts={arrayList.get(i).getEventName(),

                        arrayList.get(i).getEventImage(),
                        arrayList.get(i).getEventLocation(),
                        arrayList.get(i).getEventdate(),
                        arrayList.get(i).getDescription(),
                        arrayList.get(i).getPhone(),
                        arrayList.get(i).getTime(),
                        arrayList.get(i).getCat(),
                        arrayList.get(i).getCategoryprice(),};

                intent.putExtra("message",bodyParts);
                mContext.startActivity(intent);
                //context.startActivity(new Intent(context,FullDetailView.class));

            }
        });




    }






    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView name,time;
        TextView loc,update;
        TextView date, cost;
        ImageView im;
        TextView delete;
        Button phn, map;
        ImageButton call, location;
        private CardView r;
        RelativeLayout rrl;

        public CustomViewHolder(View view) {

            super(view);
            update=(TextView)view.findViewById(R.id.update);
            name = (TextView) view.findViewById(R.id.nam);
            loc = (TextView) view.findViewById(R.id.location);
            date = (TextView) view.findViewById(R.id.date);
            im = (ImageView) view.findViewById(R.id.image);
            time=(TextView)view.findViewById(R.id.time) ;
            delete = (TextView) view.findViewById(R.id.delete);
            //phn=(Button)view.findViewById(R.id.phone);
            call = (ImageButton) view.findViewById(R.id.imageCall);
            location = (ImageButton) view.findViewById(R.id.imageLoc);
            //map=(Button)view.findViewById(R.id.map);
            // cost = (TextView) view.findViewById(R.id.cost);
            r=(CardView) view.findViewById(R.id.re);
            rrl=(RelativeLayout)view.findViewById(R.id.rl);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
