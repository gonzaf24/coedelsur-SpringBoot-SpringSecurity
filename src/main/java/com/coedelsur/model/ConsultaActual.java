package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class ConsultaActual implements Serializable {

    private static final long serialVersionUID = 1L;
    private SelectStringValue repetirMedicamento;
    private String motivoConsulta;
    private String anemesis;
    private String examenFisico;
    private Integer peso;
    private Integer talla;
    private Integer imc;
    private Integer cintura;
    private String diagnostico;
    private String conducta;
    private Date fechaC;

    public ConsultaActual() {
        super();
    }
    
    public ConsultaActual(SelectStringValue repetirMedicamento, String motivoConsulta, String anemesis, String examenFisico, Integer peso, Integer talla,
            Integer imc, Integer cintura, String diagnostico, String conducta, Date fechaC) {
        super();
        this.repetirMedicamento = repetirMedicamento;
        this.motivoConsulta = motivoConsulta;
        this.anemesis = anemesis;
        this.examenFisico = examenFisico;
        this.peso = peso;
        this.talla = talla;
        this.imc = imc;
        this.cintura = cintura;
        this.diagnostico = diagnostico;
        this.conducta = conducta;
        this.fechaC = fechaC;
    }

    public SelectStringValue getRepetirMedicamento() {
        return repetirMedicamento;
    }

    public void setRepetirMedicamento(SelectStringValue repetirMedicamento) {
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

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public Integer getImc() {
        return imc;
    }

    public void setImc(Integer imc) {
        this.imc = imc;
    }

    public Integer getCintura() {
        return cintura;
    }

    public void setCintura(Integer cintura) {
        this.cintura = cintura;
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

    public Date getFechaC() {
        return fechaC;
    }

    public void setFechaC(Date fechaC) {
        this.fechaC = fechaC;
    }
}
