package com.coedelsur.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsultaMedica implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idDoctor;
    private Integer idPaciente;
    private Date fechaCreacion;
    private Boolean repetirMedicamento;
    private String motivoConsulta;
    private String anemesis;
    private String examenFisico;
    private Integer pesoKgs;
    private Integer tallaCms;
    private Integer imc;
    private Integer cinturaCms;
    private String diagnostico;
    private Diagnostico diagnosticoObject;
    private String conducta;
    private boolean imprimir;

    public ConsultaMedica() {
    }

    public ConsultaMedica(Integer id, Integer idDoctor, Integer idPaciente, Date fechaCreacion, Boolean repetirMedicamento, String motivoConsulta,
            String anemesis, String examenFisico, Integer pesoKgs, Integer tallaCms, Integer imc, Integer cinturaCms, String diagnostico, String conducta) {
        super();
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.fechaCreacion = fechaCreacion;
        this.repetirMedicamento = repetirMedicamento;
        this.motivoConsulta = motivoConsulta;
        this.anemesis = anemesis;
        this.examenFisico = examenFisico;
        this.pesoKgs = pesoKgs;
        this.tallaCms = tallaCms;
        this.imc = imc;
        this.cinturaCms = cinturaCms;
        this.diagnostico = diagnostico;
        this.conducta = conducta;
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

    public void setFechaCreacion(Date fehaCreacion) {
        this.fechaCreacion = fehaCreacion;
    }

    public Boolean getRepetirMedicamento() {
        return repetirMedicamento;
    }

    public void setRepetirMedicamento(Boolean repetirMedicamento) {
        this.repetirMedicamento = repetirMedicamento;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getAnemesis() {
        return anemesis;
    }

    public void setAnemesis(String anemesis) {
        this.anemesis = anemesis;
    }

    public String getExamenFisico() {
        return examenFisico;
    }

    public void setExamenFisico(String examenFisico) {
        this.examenFisico = examenFisico;
    }

    public Integer getPesoKgs() {
        return pesoKgs;
    }

    public void setPesoKgs(Integer pesoKgs) {
        this.pesoKgs = pesoKgs;
    }

    public Integer getTallaCms() {
        return tallaCms;
    }

    public void setTallaCms(Integer tallaCms) {
        this.tallaCms = tallaCms;
    }

    public Integer getImc() {
        return imc;
    }

    public void setImc(Integer imc) {
        this.imc = imc;
    }

    public Integer getCinturaCms() {
        return cinturaCms;
    }

    public void setCinturaCms(Integer cinturaCms) {
        this.cinturaCms = cinturaCms;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getConducta() {
        return conducta;
    }

    public void setConducta(String conducta) {
        this.conducta = conducta;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public Diagnostico getDiagnosticoObject() {
        return diagnosticoObject;
    }

    public void setDiagnosticoObject(Diagnostico diagnosticoObject) {
        this.diagnosticoObject = diagnosticoObject;
    }

    public String getFechaCreacionLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        return dateFormat.format(this.fechaCreacion);
    }
}
