package com.coedelsur.model;

import java.io.Serializable;

public class ConsultaDoctor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String descripcion;
    private Integer precio;
    private Integer idDoctor;
    private SelectStringValue tipoConsulta;
    private SelectStringValue especialidad;
    private Boolean habilitado;

    public ConsultaDoctor() {
        super();
    }

    public ConsultaDoctor(Integer id, String descripcion, Integer precio, Integer idDoctor, SelectStringValue tipoConsulta, SelectStringValue especialidad,
            Boolean habilitado) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idDoctor = idDoctor;
        this.tipoConsulta = tipoConsulta;
        this.especialidad = especialidad;
        this.habilitado = habilitado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public SelectStringValue getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(SelectStringValue tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public SelectStringValue getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(SelectStringValue especialidad) {
        this.especialidad = especialidad;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }
}
