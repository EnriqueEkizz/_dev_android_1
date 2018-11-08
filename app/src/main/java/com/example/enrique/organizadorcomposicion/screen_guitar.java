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

import com.example.enrique.organizadorcomposicion.Entities.clsFretsGuitar;

import java.util.ArrayList;
import java.util.List;

public class screen_guitar extends Fragment {

    List<clsFretsGuitar> ListFretsGuitar;

    public screen_guitar() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListFretsGuitar = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            ListFretsGuitar.add(new clsFretsGuitar(i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_guitar_24, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvGuitarScreen);
        AdapterFretsGuitar adapterFretsGuitar = new AdapterFretsGuitar(getContext(), ListFretsGuitar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterFretsGuitar);

        return view;
    }
}
