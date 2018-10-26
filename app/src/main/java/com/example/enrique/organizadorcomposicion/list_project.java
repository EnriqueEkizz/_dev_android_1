package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enrique.organizadorcomposicion.Entities.clsItemProject;

import java.util.ArrayList;
import java.util.List;

public class list_project extends Fragment{

    View view;
    private RecyclerView recyclerView;
    private List<clsItemProject> lstData;

    public list_project() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_project, container,false);

        recyclerView = view.findViewById(R.id.rvListProject);
        AdapterListProject adapterListProject = new AdapterListProject(getContext(), lstData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterListProject);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstData = new ArrayList<>();
        lstData.add(new clsItemProject("Proyecto 12", "29/29/2018 04:30"));
        lstData.add(new clsItemProject("Proyecto 11", "29/29/2018 04:30"));
        lstData.add(new clsItemProject("Proyecto 10", "29/29/2018 04:30"));
    }
}
