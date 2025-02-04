package com.example.myfirebaseapp.models;

public class Productos {
    private String id;
    private String titulo;
    private String descripcion;
    private String imagenUrl;

    // Constructor vacío para Firebase
    public Productos() {}

    public Productos(String id, String titulo, String descripcion, String imagenUrl) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagenUrl;
    }

    public void setImagen(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
