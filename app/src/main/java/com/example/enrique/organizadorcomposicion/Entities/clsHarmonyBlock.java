package com.example.enrique.organizadorcomposicion.Entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class clsHarmonyBlock {
    private String Recording = "";
    private String pathRecording = "";
    private ArrayList<String> HarmonyNotes;
    private boolean playing;
    private boolean inflate = false;

    public clsHarmonyBlock() {
        this.HarmonyNotes = new ArrayList<>();
        this.playing = false;
    }

    // METHODS SET
    public void setRecording(String xRecording) {
        this.Recording = xRecording;
    }
    public String getRecording() {
        return  this.Recording;
    }
    public void setPathRecording(String pathRecording) {
        this.pathRecording = pathRecording;
    }
    public void deleteRecording() {
        this.Recording = "";
    }
    public void addHarmonyNote(String xNotes) {
        this.HarmonyNotes.add(xNotes);
    }
    public void deleteHarmonyNotes(int index) {
        this.HarmonyNotes.remove(index);
    }
    public void updateHarmonyNotes(int index, String xNotes){
        this.HarmonyNotes.set(index, xNotes);
    }
    public void play() {
        this.playing = true;
    }
    public void stop() {
        this.playing = false;
    }
    public void setInflate(boolean inflate) {
        this.inflate = inflate;
    }

    // METHODS GET
    public boolean isInflate() {
        return this.inflate;
    }
    public String getPathRecording() {
        return pathRecording;
    }
    public ArrayList<String> getHarmonyNotes() {
        return HarmonyNotes;
    }
    public boolean isPlaying() {
        return this.playing;
    }
    public JSONObject getJsonBlock() {
        JSONObject jsonObject = new JSONObject();
        //Grabacion de bloque
        try {
            JSONArray arrayNotes = new JSONArray();
            for (String notes : HarmonyNotes) {
                arrayNotes.put(notes);
            }
            jsonObject.put("RECORDING", this.Recording);
            jsonObject.put("RECORDINGPATH", this.pathRecording);
            jsonObject.put("NOTES", arrayNotes);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
