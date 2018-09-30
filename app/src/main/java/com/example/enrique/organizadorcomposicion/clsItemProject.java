package com.example.enrique.organizadorcomposicion;

public class clsItemProject {
    private String titulo;
    private String fechacreacion;

    public clsItemProject() {
    }

    public clsItemProject(String titulo, String fechacreacion) {
        this.titulo = titulo;
        this.fechacreacion = fechacreacion;
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
}
