package com.example.enrique.organizadorcomposicion.Activity;

import android.app.Activity;
import android.content.Context;
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
import android.widget.LinearLayout;

import com.example.enrique.organizadorcomposicion.AdapterContentProject;
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
        // bloque 1: Grabacion + 4 notas
        clsHarmonyBlock block1 =  new clsHarmonyBlock();
            block1.setRecording("Grabacion1");
            block1.addHarmonyNotes("D#");
            block1.addHarmonyNotes("Fm");
            block1.addHarmonyNotes("Gm");
            block1.addHarmonyNotes("G#");
        // bloque 2: sin grabacion + 3 notas
        clsHarmonyBlock block2 =  new clsHarmonyBlock();
            block2.setRecording("");
            block2.addHarmonyNotes("G#");
            block2.addHarmonyNotes("Cm");
            block2.addHarmonyNotes("Dm");
        // bloque 2: sin grabacion + 3 notas
        clsHarmonyBlock block3 =  new clsHarmonyBlock();
            block3.setRecording("Grabación2");
            block3.addHarmonyNotes("A");
            block3.addHarmonyNotes("Am");
            block3.addHarmonyNotes("Cm");
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
        Display_musicalscale frgMusicalScale = new Display_musicalscale().newInstance("C");
        frgTransaction.add(R.id.frameSpaceMusicalScale, frgMusicalScale);
        frgTransaction.commit();

        //ADAPTADOR RECYCLERVIEW
        createAdapterForProject(projectStructure);

        //FLOATING ACTION BUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabOptionB);
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
