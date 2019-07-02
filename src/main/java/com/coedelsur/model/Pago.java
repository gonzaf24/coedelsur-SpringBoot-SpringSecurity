package com.coedelsur.model;

import java.io.Serializable;

public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idDoctor;
    private Integer idPaciente;
    private String concepto;
    private Integer monto;
    private String comprobantePago;
    private boolean estaPago;
    private Integer numeroTarjeta;
    private String nombreTarjeta;
    private Integer mesTarjeta;
    private Integer anioTarjeta;
    private Integer codigoVerificacionTarjeta;

    public Pago() {
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getComprobantePago() {
        return comprobantePago;
    }

    public void setComprobantePago(String comprobantePago) {
        this.comprobantePago = comprobantePago;
    }

    public boolean isEstaPago() {
        return estaPago;
    }

    public void setEstaPago(boolean estaPago) {
        this.estaPago = estaPago;
    }

    public Integer getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public Integer getMesTarjeta() {
        return mesTarjeta;
    }

    public void setMesTarjeta(Integer mesTarjeta) {
        this.mesTarjeta = mesTarjeta;
    }

    public Integer getAnioTarjeta() {
        return anioTarjeta;
    }

    public void setAnioTarjeta(Integer anioTarjeta) {
        this.anioTarjeta = anioTarjeta;
    }

    public Integer getCodigoVerificacionTarjeta() {
        return codigoVerificacionTarjeta;
    }

    public void setCodigoVerificacionTarjeta(Integer codigoVerificacionTarjeta) {
        this.codigoVerificacionTarjeta = codigoVerificacionTarjeta;
    }
}
