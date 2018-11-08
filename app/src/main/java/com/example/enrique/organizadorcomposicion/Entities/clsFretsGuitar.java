package com.example.enrique.organizadorcomposicion.Entities;

import android.util.Log;

import java.util.ArrayList;

public class clsFretsGuitar {
    private int Fret;
    private ArrayList<Boolean> Strings;

    public clsFretsGuitar(int fret) {
        //ESTABLECER TRASTE DE GUITARRA
        this.Fret = fret;
        //LLENAR POR DEFECTO TRASTE
        Strings = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            this.Strings.add(false);
        }
    }
    public void pressString(int strings) {
        this.Strings.set(strings - 1, true);
    }
    public void unpressString(int strings) {
        this.Strings.set(strings - 1, false);
    }
    public boolean isPressed(int strings){
        return Strings.get(strings - 1);
    }
    public String getNumberFret() {
        return String.valueOf(Fret);
    }
}