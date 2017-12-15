package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.bitmap;
import static com.example.moonlight.eventsystemdemo.R.id.imageView;

public class
 PostEvent extends AppCompatActivity {
    EditText eventNam, eventLo, eventDat,phone,fPrice,time,description,sPrize,tprize;
     String eventName, eventLocation, eventDate, eventImage,phn,prices,eTime,eDescription,firstP,secondP,thirdP;
    ImageView imageView;
    Bitmap bitmap;
    Button submit,map;
    GPSTracker gps;
    Spinner first,second,third;
    String longitudeLatitudeValue="";
    String method="post";


    static  PostEvent postEvent;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String n;
    private LinearLayout parentLinearLayout;
    EditText num;
    String na;
    String category1,category2;
    ListView listView;
    int ni;
    ArrayAdapter<String>adapter1;
    String catprice[]={"1","2","3","4","5"};
    String sCat;
    Spinner s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        postEvent = this;
        setContentView(R.layout.activity_post_event);
        //num=(EditText)findViewById(R.id.number_edit_text);
        listView =(ListView)findViewById(R.id.list);
        s=(Spinner)findViewById(R.id.spin);

        eventNam = (EditText) findViewById(R.id.nameEvent);
        eventLo = (EditText) findViewById(R.id.locationEvent);
        //eventDat = (EditText) findViewById(R.id.locationDate);
        imageView = (ImageView) findViewById(R.id.imageView);
        phone = (EditText) findViewById(R.id.phone);
        eventDat=(EditText)findViewById(R.id.date);

        time=(EditText)findViewById(R.id.time);
        SharedPreferences sharedPreferences = getSharedPreferences("Tool", MODE_PRIVATE);
        n = sharedPreferences.getString("idiot", "");
        description=(EditText)findViewById(R.id.description);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,catprice);
        s.setAdapter(arrayAdapter);
        sCat=s.getSelectedItem().toString();



        /*fPrice = (EditText) findViewById(R.id.firsC);
        sPrize = (EditText) findViewById(R.id.secondC);
        tprize = (EditText) findViewById(R.id.thirdC);*/

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }
    public void onAddField(View v) {
       // na = num.getText().toString();

        sCat=s.getSelectedItem().toString();

        ni = Integer.parseInt(sCat);

        List<HashMap<String,String>> s = new ArrayList<>();
        for (int i = 1; i <= ni; ++i) {

            HashMap<String,String> m = new HashMap<>();
            m.put("c1","category "+i);
            s.add(m);
        }
        ListAdapter l = new SimpleAdapter(this,s,R.layout.dynamic_row,new String[]{"c1"},new int[]{R.id.n});
        listView.setAdapter(l);






    }
   /* public  void click(View view)
    {
        String string ="";
        for(int i=0;i<listView.getCount();i++){
            View v = listView.getChildAt(i);
            EditText editText= (EditText) v.findViewById(R.id.n);
            string= string+editText.getText().toString()+",";

        }
        Toast.makeText(PostEvent.this, string, Toast.LENGTH_SHORT).show();


    }*/




    public void userClic(View view) {
        eventName = eventNam.getText().toString();
        eventLocation = eventLo.getText().toString();
        eventDate = eventDat.getText().toString();
        phn=phone.getText().toString();


        eventImage=getStringImage(bitmap);
       /*firstP=fPrice.getText().toString();
        secondP=sPrize.getText().toString();
        thirdP=tprize.getText().toString();

*/
        eTime=time.getText().toString();
        eDescription=description.getText().toString();
        String string ="";
        for(int i=0;i<listView.getCount();i++) {
            View v = listView.getChildAt(i);
            EditText editText = (EditText) v.findViewById(R.id.n);
            string = string + editText.getText().toString() + ",";
        }
        String tkn = FirebaseInstanceId.getInstance().getToken();


        RegisterLoginWithMysql cc = new RegisterLoginWithMysql(this);
            cc.execute(method, eventName, eventLocation,eventDate, eventImage, phn, tkn,eTime,eDescription,n,sCat,string);


    }

    public void gallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}