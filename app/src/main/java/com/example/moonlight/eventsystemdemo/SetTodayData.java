
package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by MoonLight on 10/10/2017.
 */


public class SetTodayData extends PagerAdapter {
    Timer timer = new Timer();

  //timer.schedule(mt, 2000, 2000);
    ArrayList<DataSave> list1;
    //Context a;
    MainActivity m;
    SetTodayData s;
    String ip=ipconfig.ip;
    Context mContext;
    ImageView im;
    String imge;
    int a;
    public SetTodayData(Context a, ArrayList<DataSave> list1) {

    this.mContext=a;
    this.list1= list1;
    }


    @Override
    public int getCount() {
        a= list1.size();
        return  a;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager v= (ViewPager) container;
        View vi= (View) object;
        v.removeView(vi);


    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        final LayoutInflater l= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = l.inflate(R.layout.today_data_main, null);
        /*ViewPager vv= (ViewPager) container;
        vv.addView(view,0);*/
       /* TextView cost=(TextView)view.findViewById(R.id.cost);
        TextView time=(TextView)view.findViewById(R.id.time) ;
        RelativeLayout r=(RelativeLayout)view.findViewById(R.id.r);


        TextView name=(TextView)view.findViewById(R.id.nam);
        TextView loc=(TextView)view.findViewById(R.id.location);
        TextView date=(TextView)view.findViewById(R.id.date);
        ImageView im=(ImageView)view.findViewById(R.id.image);
        TextView book=(TextView)view.findViewById(R.id.book);*/
        DataSave dataSave= (DataSave)list1.get(position);
        //String EvenName =dataSave.getEventName();
        /*img.setImageResource(imgs[position]);
        tv.setText("Image"+position);*/

            /*name.setText(EvenName);
            loc.setText(dataSave.getEventLocation());
            date.setText(dataSave.getEventdate());
            String imge = dataSave.getEventImage();
            cost.setText("$"+dataSave.getLat_long());
            time.setText(dataSave.getTime());*/

            /*s = new SetTodayData(a, list1);
            vv.setAdapter(s)*/;
         imge = dataSave.getEventImage();
        RelativeLayout r=(RelativeLayout)view.findViewById(R.id.r);
         im=(ImageView)view.findViewById(R.id.image);

            String ip = ipconfig.ip;
            Picasso.with(mContext).load("http://" + ip + "/event/uploads/" + imge + ".jpeg").into(im);
        container.addView(view);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext.getApplicationContext(),DetailsActivity.class);

                String[] bodyParts={list1.get(position).getEventName(),

                        list1.get(position).getEventImage(),
                        list1.get(position).getEventLocation(),
                        list1.get(position).getEventdate(),
                        list1.get(position).getDescription(),
                        list1.get(position).getPhone(),
                        list1.get(position).getTime(),
                        list1.get(position).getCat(),
                        list1.get(position).getCategoryprice(),
                };

                intent.putExtra("message",bodyParts);
                mContext.startActivity(intent);


            }
        });

        return view;

    }


}


