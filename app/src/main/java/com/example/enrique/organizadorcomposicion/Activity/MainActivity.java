package com.example.enrique.organizadorcomposicion.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.example.enrique.organizadorcomposicion.PageAdapter_sources;
import com.example.enrique.organizadorcomposicion.R;
import com.example.enrique.organizadorcomposicion.List_project;
import com.example.enrique.organizadorcomposicion.List_recorded;

public class MainActivity extends FragmentActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter_sources pageAdapter_sources;
    private FloatingActionButton fabActividadA;
    private static boolean tabRecording = true;
    private List_project listProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabPager);
        viewPager = findViewById(R.id.viewPager);
        pageAdapter_sources = new PageAdapter_sources(getSupportFragmentManager());
        fabActividadA = findViewById(R.id.fabOptionA);

        //CONSEGUIR LISTA DE GRABACION Y PROYECTOS
        /* Path de directorio de grabaciones*/
        String pathExternalStorageRecording = getExternalFilesDir("Recordings").toString();
        /* Cargando listas en pageAdapter*/
        pageAdapter_sources.addPageList(List_recorded.newInstance(pathExternalStorageRecording));
        listProject = new List_project();
        pageAdapter_sources.addPageList(listProject);
        /*ESTABLECIENDO ADAPTER DE VIEWPAGER*/
        viewPager.setAdapter(pageAdapter_sources);

        //ESTABLECER ICONO DE FLOATING ACTION BUTTON AL MOVER ENTRE TABS
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }
            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        fabActividadA.setImageResource(R.drawable.ic_tab_recorded);
                        tabRecording = true;
                        break;
                    case 1:
                        fabActividadA.setImageResource(R.drawable.ic_floating_add_project);
                        tabRecording = false;
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) { }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_recorded);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_project);

        //ESTABLECER ACCION DE FLOATING ACTION BUTTON
        fabActividadA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabRecording) { // ACTIVIDAD DE GRABACION
                    Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                    startActivity(intent);
                } else {
                    listProject.addEmptyItemProject();
                }
            }
        });
    }
}
