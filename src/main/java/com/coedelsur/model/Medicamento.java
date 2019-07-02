package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idDoctor;
    private Integer idPaciente;
    private Date fechaCreacion;
    private String medicamento;
    private Integer dosis;
    private String frecuencia;
    private Date fechaDesde;
    private Date fechaHasta;
    private String origen;
    private String observaciones;
    private boolean imprimir;

    public Medicamento() {
    }

    public Medicamento(Integer id, Integer idDoctor, Integer idPaciente, Date fechaCreacion, String medicamento, Date fechaDesde, Date fechaHasta,
            String origen, String observaciones) {
        super();
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.fechaCreacion = fechaCreacion;
        this.medicamento = medicamento;
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

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
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
