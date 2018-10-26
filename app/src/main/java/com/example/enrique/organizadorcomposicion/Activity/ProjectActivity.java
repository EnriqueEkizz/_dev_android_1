package com.example.enrique.organizadorcomposicion.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.enrique.organizadorcomposicion.AdapterContentProject;
import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;
import com.example.enrique.organizadorcomposicion.R;
import com.example.enrique.organizadorcomposicion.item_harmonyblock;

public class ProjectActivity extends AppCompatActivity {

    // CLASE PRINCIPAL DE LA ESTRUCTURA DEL PROYECTO
    clsProjectStructure projectStructure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        //SETEAR CLASE DE ESTRUCTURA DE PROYECTO CON INTENT
        projectStructure = new clsProjectStructure();

        //RECYCLERVIEW
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.rvListContentProject);

        //TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Proyecto1");
        setSupportActionBar(toolbar);

        //ADAPTADOR RECYCLERVIEW
        final AdapterContentProject adapterContentProject = new AdapterContentProject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterContentProject);

        //FLOATING ACTION BUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabOptionB);
        fab.setOnClickListener(new View.OnClickListener() {
            // AGREGAR NUEVO BLOQUE DE CIRCULO ARMONICO
            @Override
            public void onClick(View view) {
                // CREAR NUEVA CLASE DE BLOQUES ARMONICOS
                projectStructure.addHarmonyBlock(new clsHarmonyBlock());

                // CREAR FRAGMENTO BLOQUE ARMONICO
                adapterContentProject.addHarmonyBlock(new item_harmonyblock());
                adapterContentProject.notifyDataSetChanged();
            }
        });
    }

}
