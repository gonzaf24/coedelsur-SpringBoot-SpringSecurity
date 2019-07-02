package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class Diagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idDoctor;
    private Integer idPaciente;
    private Integer idConsulta;
    private Date fechaCreacion;
    private String tipo;
    private String descripcion;
    private String id10;
    private String dec10;
    private String grp10;

    public Diagnostico() {
    }

    public Diagnostico(Integer id, Integer idDoctor, Integer idPaciente, Integer idConsulta, Date fechaCreacion, String tipo, String descripcion, String id10,
            String dec10, String grp10) {
        super();
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.idConsulta = idConsulta;
        this.fechaCreacion = fechaCreacion;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.id10 = id10;
        this.dec10 = dec10;
        this.grp10 = grp10;
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

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId10() {
        return id10;
    }

    public void setId10(String id10) {
        this.id10 = id10;
    }

    public String getDec10() {
        return dec10;
    }

    public void setDec10(String dec10) {
        this.dec10 = dec10;
    }

    public String getGrp10() {
        return grp10;
    }

    public void setGrp10(String grp10) {
        this.grp10 = grp10;
    }
    
    public String getLabel() {
        return id10 + " - " + dec10 ;
    }
}
