package com.example.enrique.organizadorcomposicion.Entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class clsProjectStructure {
    private long IdDatabase;
    private String Name;
    private String Scale;
    private List<String> Recordings;
    private List<clsHarmonyBlock> HarmonyBlocks;

    public clsProjectStructure() {
        this.Recordings = new ArrayList<>();
        this.HarmonyBlocks = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getScale() {
        return Scale;
    }
    public void setScale(String scale) {
        Scale = scale;
    }

    public long getIdDatabase() {
        return IdDatabase;
    }

    public void setIdDatabase(long idDatabase) {
        IdDatabase = idDatabase;
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
    public void setHarmonyBlocks(List<clsHarmonyBlock> xHarmonyBlocks) {
        this.HarmonyBlocks = xHarmonyBlocks;
    }
    public void setDataDetailsProject(String details) {
        try {
            JSONObject main = new JSONObject(details);
            this.setName(main.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setDataContentProject(String content){
        try {
            JSONObject main = new JSONObject(content);
            // SET scale ON project
            this.Scale = main.getString("SCALE");
            // SET recordings ON project
            JSONArray arrGetting = new JSONArray(main.getJSONArray("RECORDINGS"));
            //arrGetting = main.getJSONArray("RECORDINGS");
            if (arrGetting != null) {
                for (int i = 0; i < arrGetting.length(); i++) {
                    this.Recordings.add(arrGetting.getString(i));
                }
            }
            // SET harmony block ON project
            arrGetting = main.getJSONArray("HARMONYBLOCKS");
            for (int i = 0; i < arrGetting.length(); i++) {
                // Crear clase harmonyblock
                clsHarmonyBlock harmonyBlock = new clsHarmonyBlock();

                JSONObject obj = arrGetting.getJSONObject(i);
                    harmonyBlock.setRecording(obj.getString("RECORDING"));
                    JSONArray arrNotes = obj.getJSONArray("NOTES");
                    for (int j = 0; j < arrNotes.length(); j++) {
                        harmonyBlock.addHarmonyNotes(arrNotes.getString(j));
                    }
                // Agregando clase harmonyblock a proyecto
                this.HarmonyBlocks.add(harmonyBlock);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<clsHarmonyBlock> getAllHarmonyBlock() {
        return this.HarmonyBlocks;
    }
    public int getSizeBlocks() {
        return this.HarmonyBlocks.size();
    }
    public clsHarmonyBlock getHarmonyBlock(int index) {
        return this.HarmonyBlocks.get(index);
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
