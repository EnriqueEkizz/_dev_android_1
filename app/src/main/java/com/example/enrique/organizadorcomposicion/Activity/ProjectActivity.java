package com.example.enrique.organizadorcomposicion.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.enrique.organizadorcomposicion.AdapterContentProject;
import com.example.enrique.organizadorcomposicion.Data.DatabaseHelper;
import com.example.enrique.organizadorcomposicion.Data.clsDataProjects;
import com.example.enrique.organizadorcomposicion.Display_buttonscale;
import com.example.enrique.organizadorcomposicion.Display_musicalscale;
import com.example.enrique.organizadorcomposicion.Entities.clsHarmonyBlock;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;
import com.example.enrique.organizadorcomposicion.R;

import static android.support.v7.view.ActionMode.*;

public class ProjectActivity extends AppCompatActivity implements AdapterContentProject.OnItemClickListener {
    // CLASE PRINCIPAL DE LA ESTRUCTURA DEL PROYECTO
    private clsProjectStructure projectStructure;
    private RecyclerView recyclerView;
    private AdapterContentProject adapterContentProject;
    private DatabaseHelper dataHelper;
    private android.support.v7.view.ActionMode actionMode;
    private int REQUEST_CODE = 12;
    FragmentManager frgManager;
    FragmentTransaction frgTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        dataHelper = new DatabaseHelper(this);

        //SETEAR CLASE DE ESTRUCTURA DE PROYECTO CON INTENT
        projectStructure = getProjectStructureFromDatabase(Integer.parseInt(getIntent().getStringExtra("ID_PROJECT")));
        adapterContentProject = new AdapterContentProject(this, projectStructure,REQUEST_CODE);
        adapterContentProject.setOnItemClickListener(this);

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
        frgManager = getSupportFragmentManager();
        frgTransaction = frgManager.beginTransaction();
        if (projectStructure.getContent().getScale() != 0) {
            // DESPLEGAR ESCALA
            frgTransaction.add(R.id.frameSpaceMusicalScale, new Display_musicalscale().newInstance(projectStructure.getContent().getScale(), projectStructure.getContent().getIndexScale()));
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
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK) {
                int indexActivity = data.getIntExtra("INDEX", 0);
                if (indexActivity == 0) {
                    int index = data.getIntExtra("CALL_INDEX", 0);
                    projectStructure.getContent().setHarmonyBlockRecordingName(index, data.getStringExtra("NAME"));
                    projectStructure.getContent().setHarmonyBlockRecordingPath(index, data.getStringExtra("PATH"));
                    // REBUILD ADAPTADOR DE RECYCLERVIEW
                    createAdapterForProject(projectStructure);
                } else {
                    projectStructure.getContent().setScale(data.getIntExtra("CHORD", 0));
                    projectStructure.getContent().setIndexScale(data.getIntExtra("ESCALA", 0));
                    frgTransaction = frgManager.beginTransaction();
                    frgTransaction.replace(R.id.frameSpaceMusicalScale, new Display_musicalscale().newInstance(data.getIntExtra("CHORD", 0), data.getIntExtra("ESCALA", 0)));
                    frgTransaction.commit();
                }
            }
        }
    }

    @Override
    public void onItemLongPress(int position) {
        //Log.i("ENTRO e INDICE: ", String.valueOf(position));
        actionMode = startSupportActionMode(new ActionModeCallBack(position));
        select();
    }
    private void select() {
        //adapterContentProject.select(position);
        int count = adapterContentProject.getItemCount();
        if (count == 0) {
            actionMode.finish();
            actionMode = null;
        } else {
            actionMode.setTitle("1");
            actionMode.invalidate();
        }
    }

    private void createAdapterForProject(clsProjectStructure xClsProjectStructure) {
        recyclerView.setAdapter(null);
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
    // MOVE UP harmonyblock
    // MOVE DOWN harmonyblock

    private class ActionModeCallBack implements ActionMode.Callback {
        int position;

        public ActionModeCallBack(int position) {
            this.position = position;
        }
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menu_content_project, menu);
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplication(), R.color.colorPrimary));
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_addnote:
                    projectStructure.getContent().addHarmonyNote(position,"C");
                    //createAdapterForProject(projectStructure);
                    projectStructure.getContent().getAllHarmonyBlock().get(position).setInflate(false);
                    adapterContentProject.notifyItemChanged(position);
                    actionMode.finish();
                    return true;
                case R.id.action_moveup: // Mover arriba harmonyblock
                    if (projectStructure.getContent().moveUpHarmonyBlock(position)) {
                        //createAdapterForProject(projectStructure);
                        adapterContentProject.notifyDataSetChanged();
                    }
                    actionMode.finish();
                    return true;
                case R.id.action_movedown: // Mover abajo harmonyblock
                    if (projectStructure.getContent().moveDownHarmonyBlock(position)) {
                        createAdapterForProject(projectStructure);
                    }
                    actionMode.finish();
                    return true;
                case R.id.action_delete: // Delete harmonyblock
                    projectStructure.getContent().deleteHarmonyBlock(position);
                    createAdapterForProject(projectStructure);
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            createAdapterForProject(projectStructure);
        }
    };
}
