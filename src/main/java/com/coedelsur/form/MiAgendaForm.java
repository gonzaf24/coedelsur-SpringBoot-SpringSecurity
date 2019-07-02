package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.coedelsur.model.Agenda;
import com.coedelsur.model.HistoriaClinica;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;

public class MiAgendaForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Agenda> listMiAgenda = new ArrayList<Agenda>();
    private ScheduleModel eventModel;
    private DefaultScheduleEvent addEvent;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private SelectStringValue selectedDoctor;
    private ArrayList<SelectStringValue> listDoctores = new ArrayList<SelectStringValue>();
    private ArrayList<HistoriaClinica> historiaClinicaList = new ArrayList<HistoriaClinica>();
    private Paciente paciente;
    private Agenda agenda;

    public MiAgendaForm() {
    }

    public ArrayList<Agenda> getListMiAgenda() {
        return listMiAgenda;
    }

    public void setListMiAgenda(ArrayList<Agenda> listMiAgenda) {
        this.listMiAgenda = listMiAgenda;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public DefaultScheduleEvent getAddEvent() {
        return addEvent;
    }

    public void setAddEvent(DefaultScheduleEvent addEvent) {
        this.addEvent = addEvent;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public SelectStringValue getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(SelectStringValue selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public ArrayList<SelectStringValue> getListDoctores() {
        return listDoctores;
    }

    public void setListDoctores(ArrayList<SelectStringValue> listDoctores) {
        this.listDoctores = listDoctores;
    }

    public ArrayList<HistoriaClinica> getHistoriaClinicaList() {
        return historiaClinicaList;
    }

    public void setHistoriaClinicaList(ArrayList<HistoriaClinica> historiaClinicaList) {
        this.historiaClinicaList = historiaClinicaList;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public void reset() {
        this.eventModel = null;
        this.addEvent = null;
        this.event = null;
        this.selectedDoctor = null;
        this.paciente = null;
        this.agenda = null;
        this.listDoctores = new ArrayList<SelectStringValue>();
        this.listMiAgenda = new ArrayList<Agenda>();
        this.historiaClinicaList = new ArrayList<HistoriaClinica>();
    }
}
