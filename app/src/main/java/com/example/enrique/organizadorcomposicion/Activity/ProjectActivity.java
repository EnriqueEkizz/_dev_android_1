package com.example.enrique.organizadorcomposicion.Activity;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.enrique.organizadorcomposicion.AdapterContentProject;
import com.example.enrique.organizadorcomposicion.Display_buttonscale;
import com.example.enrique.organizadorcomposicion.Display_musicalscale;
import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;
import com.example.enrique.organizadorcomposicion.R;
import com.example.enrique.organizadorcomposicion.Item_harmonyblock;

public class ProjectActivity extends AppCompatActivity {

    // CLASE PRINCIPAL DE LA ESTRUCTURA DEL PROYECTO
    private String idProject;
    clsProjectStructure projectStructure;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        //SETEAR CLASE DE ESTRUCTURA DE PROYECTO CON INTENT
        projectStructure = new clsProjectStructure();

        //CARGAR DATOS DE PROYECTO (Pruebas)
        clsHarmonyBlock block1 =  new clsHarmonyBlock();
            block1.setRecording("Grabacion1");
            block1.addHarmonyNotes("D#");
            block1.addHarmonyNotes("Fm");
            block1.addHarmonyNotes("Gm");
            block1.addHarmonyNotes("G#");
        clsHarmonyBlock block2 =  new clsHarmonyBlock();
            block2.setRecording("");
            block2.addHarmonyNotes("G#");
            block2.addHarmonyNotes("Cm");
            block2.addHarmonyNotes("Dm");
        clsHarmonyBlock block3 =  new clsHarmonyBlock();
            block3.setRecording("Grabación2");
        projectStructure.addHarmonyBlock(block1);
        projectStructure.addHarmonyBlock(block2);
        projectStructure.addHarmonyBlock(block3);

        //RECYCLERVIEW
        recyclerView = this.findViewById(R.id.rvListContentProject);

        //TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Proyecto1");
        setSupportActionBar(toolbar);

        //ESCALA MUSICAL DE PROYECTO
        FragmentManager frgManager = getSupportFragmentManager();
        FragmentTransaction frgTransaction = frgManager.beginTransaction();

        //frgTransaction.add(R.id.frameSpaceMusicalScale, new Display_musicalscale().newInstance("C"));

        //BOTON MOSTRAR ACTIVIDAD IDENTIFICAR ESCALA
        frgTransaction.add(R.id.frameSpaceMusicalScale, new Display_buttonscale());
        frgTransaction.commit();

        //ADAPTADOR RECYCLERVIEW
        createAdapterForProject(projectStructure);

        //FLOATING ACTION BUTTON
        FloatingActionButton fab = findViewById(R.id.fabOptionB);
        fab.setOnClickListener(new View.OnClickListener() {
            // AGREGAR NUEVO BLOQUE DE CIRCULO ARMONICO
            @Override
            public void onClick(View view) {
                // CREAR FRAGMENT BLOQUE ARMONICO
                projectStructure.addHarmonyBlock(new clsHarmonyBlock());
                // REBUILD ADAPTADOR DE RECYCLERVIEW
                createAdapterForProject(projectStructure);
            }
        });


    }

    private void createAdapterForProject(clsProjectStructure xClsProjectStructure) {
        recyclerView.setAdapter(null);
        AdapterContentProject adapterContentProject = new AdapterContentProject(this, xClsProjectStructure);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterContentProject);
    }
}
