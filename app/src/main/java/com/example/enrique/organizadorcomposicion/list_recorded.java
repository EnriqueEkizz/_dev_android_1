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

public class list_recorded extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private List<clsItemRecording> lstData;

    public list_recorded() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_recorded, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvListRecording);
        AdapterListRecording adapterListRecording = new AdapterListRecording(getContext(), lstData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterListRecording);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstData = new ArrayList<>();
        lstData.add(new clsItemRecording("Grabación 12", "29/29/2018 04:30", "04:30",""));
        lstData.add(new clsItemRecording("Grabación 11", "29/29/2018 02:30", "01:45",""));
        lstData.add(new clsItemRecording("Grabación 10", "29/29/2018 04:30", "04:30",""));
        lstData.add(new clsItemRecording("Grabación 9", "29/29/2018 02:30", "01:45",""));
        lstData.add(new clsItemRecording("Grabación 8", "29/29/2018 04:30", "04:30",""));
        lstData.add(new clsItemRecording("Grabación 7", "29/29/2018 02:30", "01:45",""));
        lstData.add(new clsItemRecording("Grabación 6", "29/29/2018 04:30", "04:30",""));
        lstData.add(new clsItemRecording("Grabación 5", "29/29/2018 02:30", "01:45",""));
        lstData.add(new clsItemRecording("Grabación 4", "29/29/2018 04:30", "04:30",""));
        lstData.add(new clsItemRecording("Grabación 3", "29/29/2018 02:30", "01:45",""));
        lstData.add(new clsItemRecording("Grabación 2", "29/29/2018 04:30", "04:30",""));
        lstData.add(new clsItemRecording("Grabación 1", "29/29/2018 02:30", "01:45",""));
    }
}
