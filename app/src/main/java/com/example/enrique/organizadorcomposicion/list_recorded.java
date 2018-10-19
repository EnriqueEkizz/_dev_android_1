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

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class list_recorded extends Fragment {
    private static String pathRecorded;
    View view;
    private RecyclerView recyclerView;
    private List<clsItemRecording> lstData;

    // CONSTRUCTOR
    public list_recorded() {

    }

    // RECIBIR PATH DE LISTA DE GRABACIONES
    public static list_recorded newInstance(String path) {
        list_recorded list = new list_recorded();

        Bundle bundle = new Bundle();
        bundle.putString("Path", path);
        list.setArguments(bundle);

        return list;
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
        Date date;
        String fecha;

        // OBTENER CONTENIDO DE DIRECTORIO DE GRABACIONES
        String path = getArguments().getString("Path");
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            date = new Date(files[i].lastModified());

            lstData.add(new clsItemRecording(files[i].getName(), dateFormat.format(date), "01:45",path + "/" + files[i].getName()));
        }
    }
}
