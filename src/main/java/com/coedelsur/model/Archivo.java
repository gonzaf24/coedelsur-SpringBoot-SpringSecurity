package com.coedelsur.model;

import java.io.Serializable;

public class Archivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String documento;
    private String nombre;
    private String tipo;

    public Archivo() {
        super();
    }

    public Archivo(Integer id, String documento, String nombre, String tipo) {
        super();
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
