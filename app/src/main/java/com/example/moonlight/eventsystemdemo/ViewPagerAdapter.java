package com.example.moonlight.eventsystemdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by MoonLight on 10/8/2017.
 */

class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> arrayList=new ArrayList<>();
    ArrayList<String> stringArrayList=new ArrayList<>();

    public void addFragment(Fragment fragments,String titles){
        this.arrayList.add(fragments);
        this.stringArrayList.add(titles);
    }


    public  ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }

}
