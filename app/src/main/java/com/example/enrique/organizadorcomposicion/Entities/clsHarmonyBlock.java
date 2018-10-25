package com.example.enrique.organizadorcomposicion.Entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class clsHarmonyBlock {
    private String Recording = "";
    private ArrayList<String> HarmonyNotes;

    public clsHarmonyBlock() {
        this.HarmonyNotes = new ArrayList<>();
    }

    public void setRecording(String xRecording) {
        this.Recording = xRecording;
    }
    public void deleteRecording() {
        this.Recording = "";
    }

    public void addHarmonyNotes(String xNotes) {
        this.HarmonyNotes.add(xNotes);
    }
    public void deleteHarmonyNotes(int index) {
        this.HarmonyNotes.remove(index);
    }
    public void updateHarmonyNotes(int index, String xNotes){
        this.HarmonyNotes.set(index, xNotes);
    }

    public JSONObject getJsonBlock() {
        JSONObject jsonObject = new JSONObject();
        //Grabacion de bloque
        try {
            JSONArray arrayNotes = new JSONArray();
            for (String notes : HarmonyNotes) {
                arrayNotes.put(notes);
            }
            
            jsonObject.put("RECORD", Recording);
            jsonObject.put("NOTES", arrayNotes);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
