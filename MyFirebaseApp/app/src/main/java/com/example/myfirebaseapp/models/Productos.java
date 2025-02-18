package com.example.myfirebaseapp.models;

import java.util.Objects;

public class Productos {
    private String titulo;
    private String imagen;
    private String descripcion;
    private String id;
    private boolean isFavorite;

    public Productos() {}

    public Productos(String id, String titulo, String imagen, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.isFavorite = false;
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productos productos = (Productos) o;
        return Objects.equals(id, productos.id) &&
                Objects.equals(titulo, productos.titulo) &&
                Objects.equals(descripcion, productos.descripcion) &&
                Objects.equals(imagen, productos.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion, imagen);
    }
}
