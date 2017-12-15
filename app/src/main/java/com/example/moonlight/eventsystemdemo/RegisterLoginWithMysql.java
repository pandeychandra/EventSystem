package com.example.moonlight.eventsystemdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static java.security.AccessController.getContext;

/**
 * Created by MoonLight on 9/12/2017.
 */

public class RegisterLoginWithMysql extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    ArrayList<DataSave> list1;
    View view;
    String login_name,login_pass;
    String typ;





    public RegisterLoginWithMysql(Context context) {
        this.context = context;
       // recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage("login sucess");
    }

    @Override
    protected String doInBackground(String... params) {
        String ip=ipconfig.ip;
        String reg_url = "http://"+ip+"/event/registeration.php";
        String log_url = "http://"+ip+"/event/login.php";
        String post_url = "http://"+ip+"/event/postEvent.php";
        //String download_url="http://192.168.100.44/event/download.php";
        String pay_url = "http://"+ip+"/event/pay.php";
        String nof_url = "http://"+ip+"/event/notification.php";
        String my_post = "http://"+ip+"/event/mypost.php";
        String returned = "";
        list1 = new ArrayList<>();
        String response = "";


        String method = params[0];
        if (method.equals("register")) {

            String name = params[1];
            String email = params[2];
            String password = params[3];
            String repassword = params[4];
            typ = params[5];
            String t=params[6];



            try {

                //ProgressDialog loading = ProgressDialog.show(context, "Uploading Image", "Please wait...",true,true);
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data =
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                                URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                                URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(typ, "UTF-8") + "&" +
                                URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(t, "UTF-8") + "&" +
                                URLEncoder.encode("repassword", "UTF-8") + "=" + URLEncoder.encode(repassword, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                // loading.dismiss();

                //returned= "registration sucess";
                // loading.dismiss();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("login")) {

            login_name = params[1];
            login_pass = params[2];

            try {
                URL url = new URL(log_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("loginname", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                        URLEncoder.encode("loginpass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
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

                return response;
                //returned=  "Login Sucess";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if (method.equals("reg")) {

            String name = params[1];
            String email = params[2];
            String password = params[3];
            String repassword = params[4];
            typ = params[5];


            try {

                //ProgressDialog loading = ProgressDialog.show(context, "Uploading Image", "Please wait...",true,true);
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data =
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                                URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                                URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(typ, "UTF-8") + "&" +

                                URLEncoder.encode("repassword", "UTF-8") + "=" + URLEncoder.encode(repassword, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                // loading.dismiss();

                //returned= "registration sucess";
                // loading.dismiss();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 else if (method.equals("post")) {

            String nameEvent = params[1];
            String imageEvent = params[4];
            String phn = params[5];
            String locationEvent = params[2];
            String dateEvent = params[3];

            /*String pric1 = params[6];
            String pric2 = params[7];
            String pric3 = params[8];*/
            String tok=params[6];
            String time=params[7];
            String desc=params[8];
            String id=params[9];
            String cat=params[10];
            String catprice=params[11];


            try {
                URL url = new URL(post_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(nameEvent, "UTF-8") + "&" +
                        URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(locationEvent, "UTF-8") + "&" +
                        URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(dateEvent, "UTF-8") + "&" +
                        URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phn, "UTF-8") + "&" +


                        URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imageEvent, "UTF-8") + "&" +
                        URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(tok, "UTF-8") + "&" +
                        URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8") + "&" +

                        URLEncoder.encode("desc", "UTF-8") + "=" + URLEncoder.encode(desc, "UTF-8") + "&" +
                        URLEncoder.encode("idiot", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +



                        URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(cat, "UTF-8") + "&" +
                        URLEncoder.encode("categoryprice", "UTF-8") + "=" + URLEncoder.encode(catprice, "UTF-8") ;

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


                //return response;
                //returned=  "post Sucess";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (method.equals("pay")) {

            String name = params[1];
            String ename = params[2];



            try {

                //ProgressDialog loading = ProgressDialog.show(context, "Uploading Image", "Please wait...",true,true);
                URL url = new URL(pay_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("ename", "UTF-8") + "=" + URLEncoder.encode(ename, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");



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


                // loading.dismiss();

                //returned= "registration sucess";
                // loading.dismiss();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (method.equals("nof")) {
            String token=params[1];
            String message=params[2];



                try {

                    //ProgressDialog loading = ProgressDialog.show(context, "Uploading Image", "Please wait...",true,true);
                    URL url = new URL(nof_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data =

                                    URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8") + "&" +
                                    URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");

                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    inputStream.close();





                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            return response;
        }






    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        String name=result;
       String  s = name.replaceFirst("sucess", "");
        //String nep=s.replace("")
      String f=  s.substring(0, s.length() - 3);
        String id=name.replace("sucessOrganizer","");

        if(result.contains("sucess"))
        {


            SharedPreferences sharedPreferences=context.getSharedPreferences("Tool",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("name",f);
            editor.putString("password",login_name);
            editor.putString("idiot",id);
            editor.apply();
            Toast.makeText(context, "login sucess", Toast.LENGTH_SHORT).show();


        }
    else if(result.contains("login failed"))
        {

            Toast.makeText(context, "login failed", Toast.LENGTH_SHORT).show();


        }
        else if(result.contains(""))
        {

            Toast.makeText(context, "login failed", Toast.LENGTH_SHORT).show();


        }
    /*else if(result=="Login Sucess") {

           *//* Intent i = new Intent(context, PostEvent.class);
            context.startActivity(i);*//*
           *//* SharedPreferences sharedPreferences=context.getSharedPreferences("Tool",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("name",login_name);
            editor.putString("password",login_pass);
            editor.apply();*//*
           // Toast.makeText(context, "lpogin sucess", Toast.LENGTH_SHORT).show();
        }*/
        }





}
