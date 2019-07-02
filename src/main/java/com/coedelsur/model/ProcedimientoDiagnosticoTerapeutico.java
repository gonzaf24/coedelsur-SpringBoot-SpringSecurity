package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class ProcedimientoDiagnosticoTerapeutico implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idPaciente;
    private Date fechaCreacion;

    public ProcedimientoDiagnosticoTerapeutico() {
        super();
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
