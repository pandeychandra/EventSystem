package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class MyPostActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
 MyPostAdapter myPostAdapter;
    String np;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        SharedPreferences sharedPreferences = getSharedPreferences("Tool", MODE_PRIVATE);
        np = sharedPreferences.getString("idiot", "").trim();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BackgroundTask b=new BackgroundTask();
                b.execute();



            }
        });
        BackgroundTask b=new BackgroundTask();
        b.execute();

        swipeRefreshLayout.setRefreshing(true);



    }

    class BackgroundTask extends AsyncTask<Void, DataSave, Void> {
        String ip = ipconfig.ip;
        ArrayList<DataSave> list1 = new ArrayList<>();
        String r_url = "http://" + ip + "/event/mypost.php";
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


                        URLEncoder.encode("foreign", "UTF-8") + "=" + URLEncoder.encode(np, "UTF-8");

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

                try {
                    JSONArray a = new JSONArray(response);
                    int b = a.length();

                    for (int i = 0; i < a.length(); i++) {
                        JSONObject o = a.getJSONObject(i);
                           /* String nameE = o.getString("eventname");
                            String locationE = o.getString("Location");
                            String dateE = o.getString("date");
                            String imageE = o.getString("image");*/

                        DataSave dataSave = new DataSave(o.getString("eventname"), o.getString("location"),
                                o.getString("date"), o.getString("image"), o.getString("phone"),

                                o.getString("token"), o.getString("Description"), o.getString("time"),o.getString("category")
                                , o.getString("categoryprice"),
                                o.getString("id"));
                               list1.add(dataSave);

                        //publishProgress(dataSave);
                        //list1.add(dataSave);

                    }


                } catch (Exception e) {

                    e.printStackTrace();
                }


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


          myPostAdapter   = new MyPostAdapter(MyPostActivity.this, list1);
                recyclerView.setAdapter(myPostAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(MyPostActivity.this,1));
           swipeRefreshLayout.setRefreshing(false);



        }
    }
}
