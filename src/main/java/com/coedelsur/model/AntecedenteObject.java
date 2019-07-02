package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class AntecedenteObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idDoctor;
    private Integer idPaciente;
    private Date fechaCreacion;
    private String antecedente;
    private Date fechaDesde;
    private Date fechaHasta;
    private String origen;
    private String observaciones;
    private boolean imprimir;

    public AntecedenteObject() {
    }

    public AntecedenteObject(Integer id, Integer idDoctor, Integer idPaciente, Date fechaCreacion, String antecedente, Date fechaDesde, Date fechaHasta,
            String origen, String observaciones) {
        super();
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.fechaCreacion = fechaCreacion;
        this.antecedente = antecedente;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.origen = origen;
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public String getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(String antecedente) {
        this.antecedente = antecedente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }
}
