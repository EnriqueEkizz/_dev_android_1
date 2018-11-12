package com.example.enrique.organizadorcomposicion.Data;

public class clsDataProjects {
    int id;
    String details;
    String content;

    // CONSTRUCTOR

    public clsDataProjects() {
    }
    public clsDataProjects(String details, String content) {
        this.details = details;
        this.content = content;
    }

    // GET
    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public String getContent() {
        return content;
    }

    // SET
    public void setId(int id) {
        this.id = id;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
