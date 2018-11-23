package com.example.enrique.organizadorcomposicion.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.enrique.organizadorcomposicion.AdapterContentProject;
import com.example.enrique.organizadorcomposicion.Data.DatabaseHelper;
import com.example.enrique.organizadorcomposicion.Data.clsDataProjects;
import com.example.enrique.organizadorcomposicion.Display_buttonscale;
import com.example.enrique.organizadorcomposicion.Display_musicalscale;
import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;
import com.example.enrique.organizadorcomposicion.R;

public class ProjectActivity extends AppCompatActivity {
    // CLASE PRINCIPAL DE LA ESTRUCTURA DEL PROYECTO
    private clsProjectStructure projectStructure;
    private RecyclerView recyclerView;
    private DatabaseHelper dataHelper;
    private int REQUEST_CODE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        dataHelper = new DatabaseHelper(this);

        //SETEAR CLASE DE ESTRUCTURA DE PROYECTO CON INTENT
        projectStructure = getProjectStructureFromDatabase(Integer.parseInt(getIntent().getStringExtra("ID_PROJECT")));

        //TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(projectStructure.getDetails().getTitle());
        setSupportActionBar(toolbar);

        //FLOATING ACTION BUTTON
        FloatingActionButton fab = findViewById(R.id.fabOptionB);
        fab.setOnClickListener(new View.OnClickListener() {
            // AGREGAR NUEVO BLOQUE DE CIRCULO ARMONICO
            @Override
            public void onClick(View view) {
                // ADD harmonyblock class TO projectstructure
                projectStructure.getContent().addHarmonyBlock(new clsHarmonyBlock());
                // REBUILD ADAPTADOR DE RECYCLERVIEW
                createAdapterForProject(projectStructure);
            }
        });

        //ESCALA MUSICAL DE PROYECTO
        FragmentManager frgManager = getSupportFragmentManager();
        FragmentTransaction frgTransaction = frgManager.beginTransaction();
        if (projectStructure.getContent().getScale().length() != 0) {
            // DESPLEGAR ESCALA
            frgTransaction.add(R.id.frameSpaceMusicalScale, new Display_musicalscale().newInstance("C"));
        } else {
            //BOTON MOSTRAR ACTIVIDAD IDENTIFICAR ESCALA
            frgTransaction.add(R.id.frameSpaceMusicalScale, new Display_buttonscale());
        }
        frgTransaction.commit();

        //RECYCLERVIEW
        recyclerView = this.findViewById(R.id.rvListContentProject);
        //ADAPTADOR RECYCLERVIEW
        createAdapterForProject(projectStructure);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DETAILS: ", String.valueOf(projectStructure.getIdProject()));
        Log.i("DETAILS: ", projectStructure.getDetails().getJsonDetails());
        Log.i("CONTENT: ", projectStructure.getContent().getJsonContent());

        dataHelper.updateProject(new clsDataProjects(projectStructure.getIdProject(), projectStructure.getDetails().getJsonDetails(), projectStructure.getContent().getJsonContent()));
        dataHelper.closeDB();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK) {
                int index = data.getIntExtra("CALL_INDEX", 0);
                projectStructure.getContent().setHarmonyBlockRecordingName(index, data.getStringExtra("NAME"));
                projectStructure.getContent().setHarmonyBlockRecordingPath(index, data.getStringExtra("PATH"));
                // REBUILD ADAPTADOR DE RECYCLERVIEW
                createAdapterForProject(projectStructure);
            }
        }
    }

    private void createAdapterForProject(clsProjectStructure xClsProjectStructure) {
        recyclerView.setAdapter(null);
        AdapterContentProject adapterContentProject = new AdapterContentProject(this, xClsProjectStructure, REQUEST_CODE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterContentProject);
    }
    /////////////////////////////////////////////////////
    // READ data project FROM database
    private clsProjectStructure getProjectStructureFromDatabase(int id_project) {
        clsProjectStructure structure = new clsProjectStructure();
        // READ row about id_project FROM database
        clsDataProjects dataProjects = dataHelper.getFullProject(id_project);
        // SET project structure
        // id
        structure.setIdProject(dataProjects.getId());
        // details
        structure.getDetails().setDetailsData(dataProjects.getDetails());
        // content
        structure.getContent().setContentData(dataProjects.getContent());
        return structure;
    }


}
