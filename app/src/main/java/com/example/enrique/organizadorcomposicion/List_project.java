package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.enrique.organizadorcomposicion.Data.DatabaseHelper;
import com.example.enrique.organizadorcomposicion.Data.clsDataProjects;
import com.example.enrique.organizadorcomposicion.Entities.clsItemProject;
import com.example.enrique.organizadorcomposicion.Entities.clsProjectStructure;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class List_project extends Fragment{

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<clsItemProject> lstData;
    private AdapterListProject adapterListProject;
    private DatabaseHelper dataHelper;

    public List_project() {
        lstData = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_project, container,false);
        recyclerView = view.findViewById(R.id.rvListProject);
        adapterListProject = new AdapterListProject(getContext(), lstData, dataHelper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterListProject);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //GETTING data projects FROM database
        lstData = gettingProjectsListFromDatabase();
    }

    //ADD item projects TO list
    public void addEmptyItemProject() {
        //add class
        clsItemProject item = new clsItemProject("Proyecto X", "29/29/2018 04:30");
        //ADD project TO database
        long id = addingProjectToDatabase(item);
        if (id > -1) {
            item.setIdProject(id);
            lstData.add(item);
            //actualizar adaptador
            adapterListProject.notifyDataSetChanged();
        } else {
            // ERROR
        }
    }
    //////////////////////////////////////////////////////////////////////// QUERYS
    //GET project list FROM database
    private ArrayList<clsItemProject> gettingProjectsListFromDatabase() {
        ArrayList<clsDataProjects> listData;
        ArrayList<clsItemProject> list = new ArrayList<>();
        //open data
        dataHelper = new DatabaseHelper(getContext());
        //get list
        listData = dataHelper.getDetailsList();
        //data to class
        for (clsDataProjects item : listData) {
            try {
                JSONObject json = new JSONObject(item.getDetails());
                list.add(new clsItemProject(item.getId(), json.getString("title"), json.getString("date")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        dataHelper.closeDB();
        return list;
    }
    //ADD project TO database
    private long addingProjectToDatabase(@NonNull clsItemProject itemProject) {
        long request_id = -1;

        clsDataProjects dataProjects = new clsDataProjects();
        //getting json string from item project
        dataProjects.setDetails(itemProject.getDetailsJson());
        dataProjects.setContent(new clsProjectStructure().getContent().getJsonEmptyContent());
        request_id = dataHelper.createProject(dataProjects);
        dataHelper.closeDB();
        return request_id;
    }
    //DELETE project FROM database
}
