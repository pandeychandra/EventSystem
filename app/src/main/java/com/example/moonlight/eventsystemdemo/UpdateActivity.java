package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    EditText eventNam, eventLo, eventDat,phone,fPrice,time,description,sPrize,tprize;
    String eventName, eventLocation, eventDate, eventImage,phn,prices,eTime,eDescription,firstP,secondP,thirdP;
    ImageView imageView;
    Spinner s;
    String catprice[]={"1","2","3","4","5"};
    String id;
    Bitmap bitmap;
    String tok;
    String sCat;
    ListView listView;
    String string;
    String cat;
    String price;
    String np;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        eventNam = (EditText) findViewById(R.id.nameEvent);
        eventLo = (EditText) findViewById(R.id.locationEvent);
        //eventDat = (EditText) findViewById(R.id.locationDate);
        imageView = (ImageView) findViewById(R.id.imageView);
        phone = (EditText) findViewById(R.id.phone);
        eventDat=(EditText)findViewById(R.id.date);
        description=(EditText)findViewById(R.id.description);
        String ip=ipconfig.ip;
        s=(Spinner)findViewById(R.id.spin);
        listView=(ListView)findViewById(R.id.list);
        SharedPreferences sharedPreferences = getSharedPreferences("Tool", MODE_PRIVATE);
        np = sharedPreferences.getString("idiot", "").trim();



        time=(EditText)findViewById(R.id.time);
        Bundle bundle = getIntent().getExtras();
        final String[] object = bundle.getStringArray("message");
        eventNam.setText(object[0]);
        eventLo.setText(object[2]);
        id=object[9];
        price=object[8];
        eventDat.setText(object[3]);
        time.setText(object[6]);
        phone.setText(object[5]);
        description.setText(object[4]);
        tok=object[10];
        cat=object[7];
        Picasso.with(UpdateActivity.this).load("http://" + ip + "/event/uploads/" + object[1] + ".jpeg").into(imageView);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,catprice);
        s.setAdapter(arrayAdapter);



       /*firstP=fPrice.getText().toString();
        secondP=sPrize.getText().toString();
        thirdP=tprize.getText().toString();

*/
        eTime=time.getText().toString();
        eDescription=description.getText().toString();


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
    public void onAddField(View v) {
        // na = num.getText().toString();

        sCat=s.getSelectedItem().toString();

      int  ni = Integer.parseInt(sCat);

        List<HashMap<String,String>> s = new ArrayList<>();
        for (int i = 1; i <= ni; ++i) {

            HashMap<String,String> m = new HashMap<>();
            m.put("c1","category "+i);
            s.add(m);
        }
        ListAdapter l = new SimpleAdapter(this,s,R.layout.dynamic_row,new String[]{"c1"},new int[]{R.id.n});
        listView.setAdapter(l);






    }

    public void userClic(View view)
    {
        eventName = eventNam.getText().toString();
        eventLocation = eventLo.getText().toString();
        eventDate = eventDat.getText().toString();
        phn=phone.getText().toString();
        eTime=time.getText().toString();
        eDescription=description.getText().toString();
        eventImage=getStringImage(bitmap);
        string ="";
        for(int i=0;i<listView.getCount();i++) {
            View v = listView.getChildAt(i);
            EditText editText = (EditText) v.findViewById(R.id.n);
            string = string + editText.getText().toString() + ",";
        }

        BackgroundTask b=new BackgroundTask();
        b.execute();

    }
    class BackgroundTask extends AsyncTask<Void, DataSave, Void> {
        String ip = ipconfig.ip;
        ArrayList<DataSave> list1 = new ArrayList<>();
        String r_url = "http://" + ip + "/event/update.php";
        String response = "";

        Context context;
        String[] str = null;
        Activity activity;

        public BackgroundTask() {



        }

        @Override
        protected void onPreExecute() {
            //recyclerView=(RecyclerView)activity.findViewById(R.id.recycler);
            //adapter = new MyRecyclerViewAdapter(MainActivity.this,list1);


        }

        @Override
        protected Void doInBackground(Void... params) {


            try {

                URL url = new URL(r_url);


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data =
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(eventName, "UTF-8") + "&" +
                                URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(eventLocation, "UTF-8") + "&" +
                                URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(eventDate, "UTF-8") + "&" +
                                URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phn, "UTF-8") + "&" +
                                URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(eventImage, "UTF-8") + "&" +
                               URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(tok, "UTF-8") + "&" +
                                URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(eTime, "UTF-8") + "&" +
                                URLEncoder.encode("desc", "UTF-8") + "=" + URLEncoder.encode(eDescription, "UTF-8") + "&" +
                                URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(cat, "UTF-8") + "&" +
                                URLEncoder.encode("categoryprice", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8")+
                                URLEncoder.encode("idiot", "UTF-8") + "=" + URLEncoder.encode(np, "UTF-8") + "&" +
                                URLEncoder.encode("foreign", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream inputStream = httpURLConnection.getInputStream();


                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                //StringBuilder stringBuilder=new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                    // stringBuilder.append(line+"\n");
                }
                bufferedReader.close();
                inputStream.close();




            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }



        }
}
