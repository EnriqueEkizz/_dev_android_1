package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.enrique.organizadorcomposicion.Entities.clsItemRecording;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class List_recorded extends Fragment {
    private RecyclerView recyclerView;
    private List<clsItemRecording> lstData;
    //private String pathExternalStorageRecording;

    // CONSTRUCTOR
    public List_recorded() {
    }

    // RECIBIR PATH DE LISTA DE GRABACIONES
    public static List_recorded newInstance(String path) {
        List_recorded list = new List_recorded();
        Bundle bundle = new Bundle();
        bundle.putString("Path", path);
        list.setArguments(bundle);
        return list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recorded, container, false);

        recyclerView = view.findViewById(R.id.rvListRecording);
        createAdapterForListRecording();
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

            lstData.add(new clsItemRecording(files[i].getName(), dateFormat.format(date), "",path + "/" + files[i].getName()));
        }
    }
    public int getSize() {
        return lstData.size();
    }

    public void addNewRecording(clsItemRecording item) {
        // Agregar item a lista
        lstData.add(item);
        // REBUILD adaptador
        createAdapterForListRecording();
    }

    private void createAdapterForListRecording() {
        recyclerView.setAdapter(null);
        AdapterListRecording adapterListRecording = new AdapterListRecording(getContext(), lstData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterListRecording);
    }
}
