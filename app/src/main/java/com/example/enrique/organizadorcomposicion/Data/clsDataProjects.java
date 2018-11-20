package com.example.enrique.organizadorcomposicion.Data;

public class clsDataProjects {
    long id;
    String details;
    String content;

    // CONSTRUCTOR

    public clsDataProjects() {
    }
    public clsDataProjects(String details, String content) {
        this.details = details;
        this.content = content;
    }
    public clsDataProjects(long id, String details, String content) {
        this.id = id;
        this.details = details;
        this.content = content;
    }

    // GET
    public long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public String getContent() {
        return content;
    }

    // SET
    public void setId(long id) {
        this.id = id;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
