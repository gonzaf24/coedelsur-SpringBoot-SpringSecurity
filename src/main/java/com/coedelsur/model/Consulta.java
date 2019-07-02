package com.coedelsur.model;

import java.io.Serializable;

public class Consulta implements Serializable {

    private static final long serialVersionUID = 1L;
    private Agenda agenda;
    private AgendaDia agendaDia;
    private AgendaTurno agendaTurno;
    private SelectStringValue tipoConsulta;
    private SelectStringValue especialidad;
    private Paciente paciente;
    private Doctor doctor;
    private Integer precio;
    private SelectStringValue consultorio;

    public Consulta() {
        super();
    }

    public Consulta(Agenda agenda, AgendaDia agendaDia, AgendaTurno agendaTurno, SelectStringValue tipoConsulta, SelectStringValue especialidad, Doctor doctor,
            Integer precio, SelectStringValue consultorio) {
        super();
        this.agenda = agenda;
        this.agendaDia = agendaDia;
        this.agendaTurno = agendaTurno;
        this.tipoConsulta = tipoConsulta;
        this.especialidad = especialidad;
        this.doctor = doctor;
        this.precio = precio;
        this.consultorio = consultorio;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public AgendaDia getAgendaDia() {
        return agendaDia;
    }

    public void setAgendaDia(AgendaDia agendaDia) {
        this.agendaDia = agendaDia;
    }

    public AgendaTurno getAgendaTurno() {
        return agendaTurno;
    }

    public void setAgendaTurno(AgendaTurno agendaTurno) {
        this.agendaTurno = agendaTurno;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public SelectStringValue getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(SelectStringValue consultorio) {
        this.consultorio = consultorio;
    }
}
