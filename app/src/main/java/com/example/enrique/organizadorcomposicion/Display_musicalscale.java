package com.example.enrique.organizadorcomposicion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enrique.organizadorcomposicion.Entities.clsMusicalScale;

import java.util.ArrayList;

public class Display_musicalscale extends Fragment {

    private View view;
    private int scale;
    private int indexScale;
    private clsMusicalScale musicalScale = new clsMusicalScale();

    public Display_musicalscale() {
    }

    public static Display_musicalscale newInstance(int dataScaleNote, int indexScale) {
        Display_musicalscale display_musicalscale = new Display_musicalscale();
        Bundle bundle = new Bundle();
        bundle.putInt("dataSCALENOTE", dataScaleNote);
        bundle.putInt("dataINDEXSCALE", indexScale);
        display_musicalscale.setArguments(bundle);
        return display_musicalscale;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LEYENDO NOTA MUSICAL BASE DE PROYECTO
        scale = getArguments().getInt("dataSCALENOTE");
        indexScale = getArguments().getInt("dataINDEXSCALE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int sum = 0;
        view = inflater.inflate(R.layout.display_musicalscale, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        sum = sum + scale;

        for (int n : musicalScale.ESCALAS.get(indexScale)) {
            sum = sum + n;
            if (sum > 12) {
                sum = sum - 12;
            }
            //NOTAS MUSICALES DE ESCALA
            fragmentTransaction.add(R.id.frameMusicalScale, new Item_scalenote().newInstance(musicalScale.getChordFromIndex(sum)));
        }
        //ACORDES ARMONICOS DE ESCALA
        /*fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("C"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Dm"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Em"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("F"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("G"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Am"));
        fragmentTransaction.add(R.id.frameHarmonyScale, new Item_harmonynote().newInstance("Bm"));*/
        fragmentTransaction.commit();
        return  view;
    }
}
