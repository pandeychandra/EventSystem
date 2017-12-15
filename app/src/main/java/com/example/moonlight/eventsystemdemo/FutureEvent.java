
package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by MoonLight on 10/8/2017.
 */


public class FutureEvent extends Fragment {


    RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
     private MyRecyclerViewAdapter mRecyclerviewAdapter;
    ArrayList<DataSave> list;
    SwipeRefreshLayout refreshLayout;

    public FutureEvent() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //list=new ArrayList<DataSave>();
        View view = inflater.inflate(R.layout.futureevent, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycle);
        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.sw);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {


            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    BackgroundTask c = new BackgroundTask();
                    c.execute();

                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        refreshLayout.setRefreshing(true);

        BackgroundTask c=new BackgroundTask();
        c.execute();



        return  view;
    }

    class BackgroundTask extends AsyncTask<Void, DataSave, Void> {
        ArrayList<DataSave> list1 = new ArrayList<>();
        String ip=ipconfig.ip;
        String reg_url = "http://"+ip+"/event/download1.php";
        String response = "";
        MyRecyclerViewAdapter adapter;
        Context context;
        String[] str = null;
        Activity activity;


        //  TextView textView=(TextView)context.findViewById(R.id.textView);

        public BackgroundTask() {

            //this.list1=dataSaves;

       }

        @Override
        protected void onPreExecute() {
            //recyclerView=(RecyclerView)activity.findViewById(R.id.recycler);
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                // httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

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

                    for (int i = 0; i < a.length(); i++) {
                        JSONObject o = a.getJSONObject(i);

/* String nameE = o.getString("eventname");
                            String locationE = o.getString("Location");
                            String dateE = o.getString("date");
                            String imageE = o.getString("image");*/

                        DataSave dataSave = new DataSave(o.getString("eventname"), o.getString("location"),
                                o.getString("date"), o.getString("image"), o.getString("phone"),

                                o.getString("token"), o.getString("Description"), o.getString("time"), o.getString("category")
                               , o.getString("categoryprice"),
                                o.getString("id"));
                        list1.add(dataSave);
                        publishProgress(dataSave);
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
        protected void onProgressUpdate(DataSave... values) {

           // list1.add(values[0]);
            //mRecyclerviewAdapter = new MyRecyclerViewAdapter(getActivity(),list1);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mRecyclerviewAdapter = new MyRecyclerViewAdapter(getActivity(),list1);
            recyclerView.setAdapter(mRecyclerviewAdapter);
           refreshLayout.setRefreshing(false);


        }
    }

}


