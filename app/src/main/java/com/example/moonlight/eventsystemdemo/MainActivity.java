package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    //ViewPager viewPager;
    TextView name, password;
    String n, p;
    FragmentPagerAdapter mPagerAdapter;
    MyRecyclerViewAdapter recyclerViewAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    public ArrayList<DataSave> arrayList;
    DataSave dataSave;

    ViewPager viewPager1;
    SetTodayData s;
    int bit;
    AlertDialog dialog;
    String np;

    ConnectionCheck connectionCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //String tkn = FirebaseInstanceId.getInstance().getToken();


        arrayList = new ArrayList<DataSave>();

        setSupportActionBar(toolbar);
        connectionCheck = new ConnectionCheck(this);
        String tkn = FirebaseInstanceId.getInstance().getToken();


        tabLayout = (TabLayout) findViewById(R.id.tabs);


        viewPager = (ViewPager) findViewById(R.id.view);
        viewPager1 = (ViewPager) findViewById(R.id.view1);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new PastEvent(), "past");
        viewPagerAdapter.addFragment(new FutureEvent(), "Future");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimer(), 1000, 8000);


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setMinimumWidth(tabLayout.getWidth() * 2);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        name = (TextView) header.findViewById(R.id.nam);
        password = (TextView) header.findViewById(R.id.pass);
        SharedPreferences sharedPreferences = getSharedPreferences("Tool", MODE_PRIVATE);
        n = sharedPreferences.getString("name", "");
        p = sharedPreferences.getString("password", "");

        np = sharedPreferences.getString("idiot", "").trim();
        name.setText(n);
        password.setText(p);
        BackgroundTask b = new BackgroundTask(arrayList);
        b.execute();
        if (connectionCheck.isConnected()) {
            BackgroundTask b1 = new BackgroundTask(arrayList);
            b1.execute();
        } else {
            Toast.makeText(MainActivity.this, "Plz Connect To Network First..", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, MyPostActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if (n.contains("Organizer")) {
                try {
                    Intent i = new Intent(MainActivity.this, PostEvent.class);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (n.contains("User")) {
                try {
                    Toast.makeText(getApplicationContext(), "You are user!if u want to post plz login Organizer account", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (n == "" && p == "") {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }

        } else if (id == R.id.nav_gallery) {
            if (n == "" && p == "") {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "you are login if u want to create another acount plz logout first", Toast.LENGTH_SHORT).show();
            }


        } else if (id == R.id.nav_logout) {
            SharedPreferences settings = getSharedPreferences("Tool", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
        } else if (id == R.id.nav_share) {
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(android.content.Intent.EXTRA_SUBJECT, "my App");
            share.putExtra(Intent.EXTRA_TEXT, " This is MY First App ");
            startActivity(Intent.createChooser(share, "share via"));

        } else if (id == R.id.nav_send) {
            AlertDialog.Builder mBuilder1 = new AlertDialog.Builder(MainActivity.this);
            View mView1 = getLayoutInflater().inflate(R.layout.about_us, null);

            mBuilder1.setView(mView1);
            final AlertDialog dialog1 = mBuilder1.create();
            dialog1.show();

        } else if (id == R.id.nav_mypost) {
            if (np == "") {
                Toast.makeText(getApplicationContext(), "you are not Login", Toast.LENGTH_SHORT).show();

            } else if (n.contains("User")) {
                try {
                    Toast.makeText(getApplicationContext(), "You are user!if u want to post plz login Organizer account", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Intent i = new Intent(MainActivity.this, MyPostActivity.class);
                startActivity(i);
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    class BackgroundTask extends AsyncTask<Void, DataSave, Void> {
        String ip = ipconfig.ip;
        ArrayList<DataSave> list1 = new ArrayList<>();
        String reg_url = "http://" + ip + "/event/download.php";
        String response = "";
        MyRecyclerViewAdapter adapter;
        Context context;
        String[] str = null;
        Activity activity;


        //  TextView textView=(TextView)context.findViewById(R.id.textView);

        public BackgroundTask(ArrayList<DataSave> dataSaves) {

            //this.list1 = dataSaves;


        }

        @Override
        protected void onPreExecute() {
            //recyclerView=(RecyclerView)activity.findViewById(R.id.recycler);
            //adapter = new MyRecyclerViewAdapter(MainActivity.this,list1);


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
                    bit = a.length();

                    for (int i = 0; i < a.length(); i++) {
                        JSONObject o = a.getJSONObject(i);
                           /* String nameE = o.getString("eventname");
                            String locationE = o.getString("Location");
                            String dateE = o.getString("date");
                            String imageE = o.getString("image");*/

                        DataSave dataSave = new DataSave(o.getString("eventname"), o.getString("location"),
                                o.getString("date"), o.getString("image"), o.getString("phone"),

                                o.getString("token"), o.getString("Description"), o.getString("time"), o.getString("category")
                                , o.getString("categoryprice")
                                , o.getString("id"));
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
        protected void onPostExecute(Void aVoid) {

            s = new SetTodayData(MainActivity.this, list1);
            viewPager1.setAdapter(s);

        }
    }

    class MyTimer extends TimerTask {

        public void run() {


            MainActivity.this.runOnUiThread(new Runnable() {

                Random rand = new Random();

                public void run() {
                    if (viewPager1.getCurrentItem() == 0) {
                        viewPager1.setCurrentItem(1);

                    } else if (viewPager1.getCurrentItem() == 1) {
                        viewPager1.setCurrentItem(2);

                    } else if (viewPager1.getCurrentItem() == 2) {
                        viewPager1.setCurrentItem(0);

                    }
                 /*   for (int c = 0; c <bit; c++) {

                            if (viewPager1.getCurrentItem() == c) {

                                viewPager1.setCurrentItem(c + 1);


                            }
                        }*/


                }
            });
        }
    }
}

