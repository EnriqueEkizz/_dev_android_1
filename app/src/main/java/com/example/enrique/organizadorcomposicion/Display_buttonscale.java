package com.example.enrique.organizadorcomposicion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.enrique.organizadorcomposicion.Activity.ProjectActivity;
import com.example.enrique.organizadorcomposicion.Activity.ScaleActivity;

public class Display_buttonscale extends Fragment {
    private int REQUEST_CODE = 12;

    public Display_buttonscale() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.display_buttonscale, container, false);
        Button btnShowScaleActivity = view.findViewById(R.id.btnAddScale);
        btnShowScaleActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScaleActivity.class);
                getActivity().startActivityForResult(intent, REQUEST_CODE);
            }
        });
        return view;
    }
}
