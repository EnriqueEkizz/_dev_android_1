package com.example.enrique.organizadorcomposicion.Entities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class clsProjectStructure {
    private long IdProject;
    private clsDetailProjet Details;
    private clsContentProject Content;

    public clsProjectStructure() {
        this.Details = new clsDetailProjet();
        this.Content = new clsContentProject();
    }

    // GETTER
    public long getIdProject() {
        return IdProject;
    }
    public clsDetailProjet getDetails() {
        return Details;
    }
    public clsContentProject getContent() {
        return Content;
    }
    // SETTER
    public void setIdProject(long idProject) {
        IdProject = idProject;
    }

    ///////////////////////////
    // CLASE DETAILS: contiene los detalles de un proyecto
    public class clsDetailProjet{
        private String Title, Date;
        public clsDetailProjet() {

        }
        // GETTER
        public String getTitle() {
            return Title;
        }
        public String getDate() {
            return Date;
        }

        // METHODS SET
        public void setDetailsData(String details) {
            try {
                JSONObject main = new JSONObject(details);
                this.Title = main.getString("title");
                this.Date = main.getString("date");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // METHODS GET
        public String getJsonDetails() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("title", this.Title);
                jsonObject.put("date", this.Date);
                return jsonObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "err";
        }
    }
    // CLASE CONTENIDO: contiene los detalles del contenido de un proyecto
    public class clsContentProject{
        private String Scale;
        private List<String> Recordings;
        private List<clsHarmonyBlock> HarmonyBlocks;

        public clsContentProject(){
            this.Recordings = new ArrayList<>();
            this.HarmonyBlocks = new ArrayList<>();
        }
        // GETTER
        public String getScale() {
            return Scale;
        }
        // SETTER
        public void setScale(String scale) {
            Scale = scale;
        }
        // METHODS SET
        public void setContentData(String content){
            try {
                JSONObject main = new JSONObject(content);
                // SET scale ON project
                this.Scale = main.getString("SCALE");
                // SET recordings ON project
                JSONArray arrGetting = main.getJSONArray("RECORDINGS");
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
                    harmonyBlock.setPathRecording(obj.getString("RECORDINGPATH"));
                    JSONArray arrNotes = obj.getJSONArray("NOTES");
                    for (int j = 0; j < arrNotes.length(); j++) {
                        harmonyBlock.addHarmonyNote(arrNotes.getString(j));
                    }
                    // Agregando clase harmonyblock a proyecto
                    this.HarmonyBlocks.add(harmonyBlock);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public void addHarmonyBlock(clsHarmonyBlock xHarmonyBlock) {
            this.HarmonyBlocks.add(xHarmonyBlock);
        }
        public void setHarmonyBlockRecordingName(int index, String path) {
            HarmonyBlocks.get(index).setRecording(path);
        }
        public void setHarmonyBlockRecordingPath(int index, String path) {
            HarmonyBlocks.get(index).setPathRecording(path);
        }
        // METHODS GET
        public List<clsHarmonyBlock> getAllHarmonyBlock() {
            return this.HarmonyBlocks;
        }
        public String getJsonContent() {
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
                jsonObject.put("SCALE", this.Scale);
                jsonObject.put("RECORDINGS", arrRecorded);
                jsonObject.put("HARMONYBLOCKS", arrBlocks);

                return  jsonObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "err";
        }
        public String getJsonEmptyContent() {
            try{
                JSONObject contentProject = new JSONObject();
                contentProject.put("SCALE","");
                contentProject.put("RECORDINGS", new JSONArray());
                contentProject.put("HARMONYBLOCKS", new JSONArray());

                Log.i("STRINGO: ", contentProject.toString());
                return contentProject.toString();
            } catch (Exception e){
                e.printStackTrace();
            }
            return "err";
        }

        // METHODS ACTIONS
        public boolean moveUpHarmonyBlock(int index) {
            if (index > 0) {
                // Tomando bloques
                clsHarmonyBlock blockToUp = HarmonyBlocks.get(index);
                clsHarmonyBlock blockToDown = HarmonyBlocks.get(index - 1);
                // Cambiando de lugar
                HarmonyBlocks.set(index - 1, blockToUp);
                HarmonyBlocks.set(index, blockToDown);
                return true;
            } else {
                return false;
            }
        }
        public boolean moveDownHarmonyBlock(int index) {
            if (index < HarmonyBlocks.size() - 1) {
                clsHarmonyBlock blockToDown = HarmonyBlocks.get(index);
                clsHarmonyBlock blockToUp = HarmonyBlocks.get(index + 1);
                // Cambiando de lugar
                HarmonyBlocks.set(index + 1, blockToDown);
                HarmonyBlocks.set(index, blockToUp);
                return true;
            } else {
                return false;
            }
        }
        public void deleteHarmonyBlock(int index) {
            HarmonyBlocks.remove(index);
        }
        public void addHarmonyNote(int index, String note) {
            HarmonyBlocks.get(index).addHarmonyNote(note);
        }
    }
}
