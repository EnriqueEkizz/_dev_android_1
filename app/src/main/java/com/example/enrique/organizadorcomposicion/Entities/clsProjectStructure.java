package com.example.enrique.organizadorcomposicion.Entities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class clsProjectStructure {
    private ArrayList<String> Recordings;
    private ArrayList<clsHarmonyBlock> HarmonyBlocks;

    public clsProjectStructure() {
        this.Recordings = new ArrayList<>();
        this.HarmonyBlocks = new ArrayList<>();
    }

    public void addRecording(String xRecording) {
        this.Recordings.add(xRecording);
    }
    public void deleteRecording(int index) {
        this.Recordings.remove(index);
    }

    public void addHarmonyBlock(clsHarmonyBlock xHarmonyBlock) {
        this.HarmonyBlocks.add(xHarmonyBlock);
    }

    public void getJsonForSave() {
        JSONObject jsonObject = new JSONObject();


        try {
            // CONSEGUIR GRABACIONES
            JSONArray arrRecorded = new JSONArray();
            for (String recorded : Recordings) {
                arrRecorded.put(recorded);
            }
            // CONSEGUIR BLOQUES
            JSONArray arrBlocks = new JSONArray();
            for (clsHarmonyBlock harmonyBlock : HarmonyBlocks) {
                arrBlocks.put(harmonyBlock.getJsonBlock());
            }

            jsonObject.put("RECORDINGS", arrRecorded);
            jsonObject.put("BLOCKS", arrBlocks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
