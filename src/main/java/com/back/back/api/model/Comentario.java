package com.back.back.api.model;

import java.util.ArrayList;

public class Comentario {
    private int id;
    private String comentario;
    private int puntaje;
    private String name;
    private String url;
    private ArrayList<Comentario> subcomentario;
    public Comentario(){

    }
    public Comentario(int id, String comentario, int puntaje, String name, String url, ArrayList<Comentario> subcomentario) {
        this.id = id;
        this.comentario = comentario;
        this.puntaje = puntaje;
        this.name = name;
        this.url = url;
        this.subcomentario = subcomentario;
    }
    public Comentario buscarComentarioPorId(int idBuscado) {
        if (this.id == idBuscado) {
            return this; // Encontramos el comentario con el ID buscado
        }

        if (subcomentario != null) {
            for (Comentario subcom : subcomentario) {
                Comentario comentarioEncontrado = subcom.buscarComentarioPorId(idBuscado);
                if (comentarioEncontrado != null) {
                    return comentarioEncontrado; // Encontramos el comentario en los subcomentarios
                }
            }
        }

        return null; // No se encontró el comentario con el ID buscado
    }
    public Comentario encontrarComentarioIdMayor() {
        Comentario comentarioMayor = this;

        if (subcomentario != null) {
            for (Comentario subcom : subcomentario) {
                Comentario comentarioEncontrado = subcom.encontrarComentarioIdMayor();
                if (comentarioEncontrado.getId() > comentarioMayor.getId()) {
                    comentarioMayor = comentarioEncontrado;
                }
            }
        }

        return comentarioMayor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public ArrayList<Comentario> getSubcomentario() {
        return subcomentario;
    }
    public void setSubcomentario(ArrayList<Comentario> subcomentario) {
        this.subcomentario = subcomentario;
    }
    
    


}
