package com.coedelsur.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Antecedente implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idPaciente;
    private Integer idDoctor;
    private Date fechaCreacion;
    private AntecedenteObject personal;
    private AntecedenteObject familiar;
    private Medicamento medicamento;
    private boolean imprimir;

    public Antecedente(Integer id, Integer idPaciente, Integer idDoctor, Date fechaCreacion, AntecedenteObject personal, AntecedenteObject familiar,
            Medicamento medicamento) {
        super();
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
        this.fechaCreacion = fechaCreacion;
        this.personal = personal;
        this.familiar = familiar;
        this.medicamento = medicamento;
    }

    public Antecedente() {
        this.personal = new AntecedenteObject();
        this.familiar = new AntecedenteObject();
        this.medicamento = new Medicamento();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public AntecedenteObject getPersonal() {
        return personal;
    }

    public void setPersonal(AntecedenteObject personal) {
        this.personal = personal;
    }

    public AntecedenteObject getFamiliar() {
        return familiar;
    }

    public void setFamiliar(AntecedenteObject familiar) {
        this.familiar = familiar;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    public String getFechaCreacionLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        return dateFormat.format(this.fechaCreacion);
    }
}
