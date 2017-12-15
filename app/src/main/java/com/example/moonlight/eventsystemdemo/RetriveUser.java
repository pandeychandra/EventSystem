package com.example.moonlight.eventsystemdemo;

/**
 * Created by MoonLight on 11/8/2017.
 */

public class RetriveUser {
    public RetriveUser(String n,String e)
    {
       this.setName(n);
        this.setEmail(e);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String name,email;
}
