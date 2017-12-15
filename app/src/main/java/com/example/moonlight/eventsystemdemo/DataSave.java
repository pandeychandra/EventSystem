package com.example.moonlight.eventsystemdemo;

/**
 * Created by MoonLight on 9/18/2017.
 */

public class DataSave {
    String eventName;
    String eventLocation;
    String eventdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;





    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    String eventImage;

    public String getCategoryprice() {
        return categoryprice;
    }

    public void setCategoryprice(String categoryprice) {
        this.categoryprice = categoryprice;
    }

    String categoryprice;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    String token;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    String cat;

    public DataSave(String eventname, String location, String date,String img,String phn,
                    String toke,
                    String desc,String time,String cat,String categoryprice
    ,String id) {
        this.setEventName(eventname);
        this.setEventLocation(location);
        this.setEventdate(date);
        this.setEventImage(img);
        this.setPhone(phn);


        this.setToken(toke);
        this.setDescription(desc);
        this.setTime(time);
        this.setCat(cat);
        this.setCategoryprice(categoryprice);
        this.setId(id);

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }


}
