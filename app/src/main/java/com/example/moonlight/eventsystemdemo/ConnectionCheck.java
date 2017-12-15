package com.example.moonlight.eventsystemdemo;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by MoonLight on 10/15/2017.
 */

public class ConnectionCheck {
    Activity context;

    static ConnectivityManager connectivityManager;

    public ConnectionCheck(Activity context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        this.context = context;
        //  this.connectivityManager=connectivityManager;
    }

    public static Boolean isConnected() {
        Boolean isConnected = false;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            isConnected = true;

        }
        return isConnected;

    }
}

