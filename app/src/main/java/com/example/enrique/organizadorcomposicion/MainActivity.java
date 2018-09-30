package com.example.enrique.organizadorcomposicion;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends FragmentActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter_sources pageAdapter_sources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tabPager);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        pageAdapter_sources = new PageAdapter_sources(getSupportFragmentManager());

        //CONSEGUIR LISTA DE GRABACION Y PROYECTOS
        pageAdapter_sources.addPageList(new list_recorded());
        pageAdapter_sources.addPageList(new list_project());

        viewPager.setAdapter(pageAdapter_sources);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_recorded);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_project);
    }
}
