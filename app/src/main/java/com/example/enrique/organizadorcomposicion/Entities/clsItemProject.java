package com.example.enrique.organizadorcomposicion.Entities;

import org.json.JSONException;
import org.json.JSONObject;

public class clsItemProject {
    private long idProject;
    private String titulo;
    private String fechacreacion;

    //CONTRUCTOR
    public clsItemProject() {
    }
    public clsItemProject(long idProject, String titulo, String fechacreacion) {
        this.idProject = idProject;
        this.titulo = titulo;
        this.fechacreacion = fechacreacion;
    }
    public clsItemProject(String titulo, String fechacreacion) {
        this.titulo = titulo;
        this.fechacreacion = fechacreacion;
    }
    //GETTER
    public String getTitulo() {
        return titulo;
    }
    public String getFechacreacion() {
        return fechacreacion;
    }
    public long getIdProject() {
        return idProject;
    }

    //SETTER
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }
    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

    //PARSER
    public String getDetailsJson(){
        try{
            JSONObject detailProject = new JSONObject();
            detailProject.put("title", this.titulo);
            detailProject.put("date", this.fechacreacion);

            return detailProject.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "err";
    }
}
