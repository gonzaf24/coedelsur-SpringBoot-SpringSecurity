package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idDoctor;
    private Integer idPaciente;
    private Integer idConsultorio;
    private Integer idConsulta;
    private Date dia;
    private Date horaDesde;
    private Date horaHasta;
    private Boolean libre;
    private Boolean aceptado;
    private String comentario;
    private String labelDia;
    private String labelHoraDesde;
    private String labelHoraHasta;
    private SelectStringValue tipoConsulta;
    private SelectStringValue consultorio;
    private SelectStringValue especialidad;
    private Paciente paciente;

    public Agenda() {
    }

    public Agenda(Integer id, Integer idDoctor, Integer idPaciente, Integer idConsultorio, Integer idConsulta, Date dia, Date horaDesde, Date horaHasta,
            Boolean libre, Boolean aceptado, String comentario, String labelDia, String labelHoraDesde, String labelHoraHasta, SelectStringValue tipoConsulta,
            SelectStringValue consultorio) {
        super();
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.idConsultorio = idConsultorio;
        this.idConsulta = idConsulta;
        this.dia = dia;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.libre = libre;
        this.aceptado = aceptado;
        this.comentario = comentario;
        this.labelDia = labelDia;
        this.labelHoraDesde = labelHoraDesde;
        this.labelHoraHasta = labelHoraHasta;
        this.tipoConsulta = tipoConsulta;
        this.consultorio = consultorio;
    }

    public Agenda(Integer idDoctor, Integer idConsultorio, Integer idConsulta, Date dia, Date horaDesde, Date horaHasta) {
        super();
        this.idDoctor = idDoctor;
        this.idConsultorio = idConsultorio;
        this.idConsulta = idConsulta;
        this.dia = dia;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
    }

    public Agenda(Integer id, Integer idDoctor, Integer idPaciente, Integer idConsultorio, Integer idConsulta, Date dia, Date horaDesde, Date horaHasta,
            Boolean libre, Boolean aceptado) {
        super();
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.idConsultorio = idConsultorio;
        this.idConsulta = idConsulta;
        this.dia = dia;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.libre = libre;
        this.aceptado = aceptado;
    }

    public Agenda(Integer id, Integer idDoctor, Integer idPaciente, Integer idConsultorio, Integer idConsulta, Date dia, Date horaDesde, Date horaHasta,
            Boolean libre, Boolean aceptado, String labelDia, String labelHoraDesde, String labelHoraHasta) {
        super();
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.idConsultorio = idConsultorio;
        this.idConsulta = idConsulta;
        this.dia = dia;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.libre = libre;
        this.aceptado = aceptado;
        this.labelDia = labelDia;
        this.labelHoraDesde = labelHoraDesde;
        this.labelHoraHasta = labelHoraHasta;
    }

    public Agenda(Integer idDoctor, Integer idConsultorio, Integer idConsulta, Date dia, Date horaDesde, Date horaHasta, String labelDia,
            String labelHoraDesde, String labelHoraHasta) {
        super();
        this.idDoctor = idDoctor;
        this.idConsultorio = idConsultorio;
        this.idConsulta = idConsulta;
        this.dia = dia;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.labelDia = labelDia;
        this.labelHoraDesde = labelHoraDesde;
        this.labelHoraHasta = labelHoraHasta;
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

    public Integer getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(Integer idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Date horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
    }

    public Boolean getLibre() {
        return libre;
    }

    public void setLibre(Boolean libre) {
        this.libre = libre;
    }

    public Boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getLabelDia() {
        return labelDia;
    }

    public void setLabelDia(String labelDia) {
        this.labelDia = labelDia;
    }

    public String getLabelHoraDesde() {
        return labelHoraDesde;
    }

    public void setLabelHoraDesde(String labelHoraDesde) {
        this.labelHoraDesde = labelHoraDesde;
    }

    public String getLabelHoraHasta() {
        return labelHoraHasta;
    }

    public void setLabelHoraHasta(String labelHoraHasta) {
        this.labelHoraHasta = labelHoraHasta;
    }

    public SelectStringValue getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(SelectStringValue consultorio) {
        this.consultorio = consultorio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
}
