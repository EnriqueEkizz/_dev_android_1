package com.example.enrique.organizadorcomposicion.Entities;

public class clsItemRecording{
    private String titulo;
    private String fechacreacion;
    private String duracion;
    private String path;

    public clsItemRecording() {
    }

    public clsItemRecording(String titulo, String fechacreacion, String duracion, String path) {
        this.titulo = titulo;
        this.fechacreacion = fechacreacion;
        this.duracion = duracion;
        this.path = path;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
