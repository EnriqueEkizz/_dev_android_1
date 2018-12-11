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

import java.util.ArrayList;

public class Screen_percentage extends Fragment {

    public AdapterPercentageScale adapterPercentageScale;
    RecyclerView recyclerView;
    public ArrayList<Integer> arrPercentages;

    public Screen_percentage() {
    }

    public static Screen_percentage newInstance(ArrayList<Integer> percentages) {
        Screen_percentage list = new Screen_percentage();
        Bundle bundle = new Bundle();
        bundle.putSerializable("array", percentages);
        list.setArguments(bundle);
        return list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_percentage,container,false);
        recyclerView = view.findViewById(R.id.rvPercentageScreen);
        adapterPercentageScale = new AdapterPercentageScale(arrPercentages, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterPercentageScale);

        return view;
    }
}
