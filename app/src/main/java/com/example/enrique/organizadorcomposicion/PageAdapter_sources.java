package com.example.enrique.organizadorcomposicion;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter_sources extends FragmentPagerAdapter {

    private final static byte TOTAL_PAGES = 2;
    private final List<Fragment> lstPages = new ArrayList<>();


    public PageAdapter_sources(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return lstPages.get(i);
    }

    @Override
    public int getCount() {
        return (int)TOTAL_PAGES;
    }

    public void addPageList (Fragment fragment) {
        lstPages.add(fragment);
    }
}
