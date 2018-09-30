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

import java.util.ArrayList;
import java.util.List;

public class list_project extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<clsItemProject> lstData;

    public list_project() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_project, container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvListProject);
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
        lstData.add(new clsItemProject("Proyecto 11", "29/29/2018 02:30"));
        lstData.add(new clsItemProject("Proyecto 10", "29/29/2018 04:30"));
        lstData.add(new clsItemProject("Proyecto 9", "29/29/2018 02:30"));
        lstData.add(new clsItemProject("Proyecto 8", "29/29/2018 04:30"));
        lstData.add(new clsItemProject("Proyecto 7", "29/29/2018 02:30"));
        lstData.add(new clsItemProject("Proyecto 6", "29/29/2018 04:30"));
        lstData.add(new clsItemProject("Proyecto 5", "29/29/2018 02:30"));
        lstData.add(new clsItemProject("Proyecto 4", "29/29/2018 04:30"));
        lstData.add(new clsItemProject("Proyecto 3", "29/29/2018 02:30"));
        lstData.add(new clsItemProject("Proyecto 2", "29/29/2018 04:30"));
        lstData.add(new clsItemProject("Proyecto 1", "29/29/2018 02:30"));
    }
}
