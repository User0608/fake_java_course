package com.saucedo.courses;

public class Course {
    private String title;
    private String categoria;
    private String date;
    private String duration;
    private String alerMessage;

    public Course() {
    }

    public Course(String title, String categoria, String date, String duration, String alerMessage) {
        this.title = title;
        this.categoria = categoria;
        this.date = date;
        this.duration = duration;
        this.alerMessage = alerMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlerMessage() {
        return alerMessage;
    }

    public void setAlerMessage(String alerMessage) {
        this.alerMessage = alerMessage;
    }
}
