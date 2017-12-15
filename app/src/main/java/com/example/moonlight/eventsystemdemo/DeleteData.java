package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by MoonLight on 12/14/2017.
 */

public class DeleteData extends AsyncTask<String, Void, String> {
    MyPostAdapter myPostAdapter;
    String ip = ipconfig.ip;
    //ArrayList<DataSave> list1 = new ArrayList<>();
    String delete_url = "http://" + ip + "/event/delete.php";
    String response = "";

    Context context;
    String[] str = null;
    Activity activity;
    String iid;

    public DeleteData(String id) {
        this.iid = id;
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {


        try {


            URL url = new URL(delete_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data =


                    URLEncoder.encode("foreign", "UTF-8") + "=" + URLEncoder.encode(iid, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            //String response = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();






        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.contains("sucess"))
        {


            /*Intent i=new Intent(context,MyPostActivity.class);
            context.startActivity(i);*/
        }





    }
}