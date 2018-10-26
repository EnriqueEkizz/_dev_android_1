package com.example.enrique.organizadorcomposicion.Entities;

import java.util.ArrayList;

public class clsMusicalScale {
    private ArrayList<String> ListNotes;
    private ArrayList<String> ListHarmonyNotes;

    public clsMusicalScale() {
    }

    public void addNote(String note) {
        this.ListNotes.add(note);
    }
    public void addHarmonyNote(String harmonyNote) {
        this.ListHarmonyNotes.add(harmonyNote);
    }
    public ArrayList<String> getListNotes() {
        return this.ListNotes;
    }
    public ArrayList<String> getListHarmonyNotes() {
        return this.ListHarmonyNotes;
    }
}
