package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Display_musicalscale extends Fragment {

    private View view;
    private String MusicalScale;

    public Display_musicalscale() {
    }

    public static Display_musicalscale newInstance(String dataScaleNote) {
        Display_musicalscale display_musicalscale = new Display_musicalscale();
        Bundle bundle = new Bundle();
        bundle.putString("dataSCALENOTE", dataScaleNote);
        display_musicalscale.setArguments(bundle);
        return display_musicalscale;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LEYENDO NOTA MUSICAL BASE DE PROYECTO
        MusicalScale = getArguments().getString("dataSCALENOTE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.display_musicalscale, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //NOTAS MUSICALES DE ESCALA
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance("C"));
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance("D"));
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance("E"));
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance("F"));
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance("G"));
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance("A"));
        fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance("B"));
        //ACORDES ARMONICOS DE ESCALA
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("C"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Dm"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Em"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("F"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("G"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Am"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Bm"));
        fragmentTransaction.commit();
        return  view;
    }
}
