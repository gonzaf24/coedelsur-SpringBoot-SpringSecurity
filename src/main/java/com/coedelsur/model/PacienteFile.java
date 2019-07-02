package com.coedelsur.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PacienteFile implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idPaciente;
    private Integer idDoctor;
    private String tipoFile;
    private String descripcion;
    private Date fechaCreacion;
    private Archivo archivo;

    public PacienteFile() {
    }

    public PacienteFile(Integer id, Integer idPaciente, Integer idDoctor, String descripcion, Date fechaCreacion, String tipoFile, Archivo archivo) {
        super();
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.tipoFile = tipoFile;
        this.archivo = archivo;
    }
    
    public PacienteFile(Integer id, Integer idPaciente, Integer idDoctor, String descripcion, Date fechaCreacion, String tipoFile) {
        super();
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.tipoFile = tipoFile;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipoFile() {
        return tipoFile;
    }

    public void setTipoFile(String tipoFile) {
        this.tipoFile = tipoFile;
    }

    public Archivo getArchivo() {
        return archivo;
    }

    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }

    public String getFechaCreacionLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        return dateFormat.format(this.fechaCreacion);
    }
}
