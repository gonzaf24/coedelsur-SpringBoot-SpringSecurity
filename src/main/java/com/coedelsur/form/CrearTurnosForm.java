package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.coedelsur.model.Agenda;
import com.coedelsur.model.AgendaDia;
import com.coedelsur.model.AgendaHoraTurno;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.SelectStringValue;
import com.google.gson.Gson;

public class CrearTurnosForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Date> listDiasParaTurnos = new ArrayList<Date>();
    private ArrayList<AgendaDia> listAgendaDia = new ArrayList<AgendaDia>();
    private ArrayList<AgendaHoraTurno> listAgendaHoraTurno = new ArrayList<AgendaHoraTurno>();
    private Date diaSelected;
    private Date today = new Date();
    private AgendaHoraTurno agendaHoraTurno;
    private ArrayList<SelectStringValue> listIntervalos = new ArrayList<SelectStringValue>(Arrays.asList(new SelectStringValue("15 mins.", 15), new SelectStringValue("30 mins.", 30), new SelectStringValue("45 mins.", 45), new SelectStringValue("60 mins.", 60)));
    private SelectStringValue selectedIntervalo;
    private Date desdeHoraSel = obtenerDiaHoyHorayMinutoCero();
    private Date hastaHoraSel = obtenerDiaHoyHorayMinutoCero();
    private ArrayList<ConsultaDoctor> listConsultas = new ArrayList<ConsultaDoctor>();
    private SelectStringValue selectedDoctor;
    private ArrayList<SelectStringValue> listConsultorios = new ArrayList<SelectStringValue>();
    private SelectStringValue selectedConsultorio;
    private ArrayList<Agenda> listaAgendaGenerada = new ArrayList<Agenda>();
    private ArrayList<SelectStringValue> listDoctores = new ArrayList<SelectStringValue>();

    public CrearTurnosForm() {
    }

    public ArrayList<Date> getListDiasParaTurnos() {
        return listDiasParaTurnos;
    }

    public void setListDiasParaTurnos(ArrayList<Date> listDiasParaTurnos) {
        this.listDiasParaTurnos = listDiasParaTurnos;
    }

    public Date getDiaSelected() {
        return diaSelected;
    }

    public void setDiaSelected(Date diaSelected) {
        this.diaSelected = diaSelected;
    }

    public String getListDiasParaTurnosAsJSON() {
        return new Gson().toJson(listDiasParaTurnos);
    }

    public ArrayList<AgendaDia> getListAgendaDia() {
        return listAgendaDia;
    }

    public void setListAgendaDia(ArrayList<AgendaDia> listAgendaDia) {
        this.listAgendaDia = listAgendaDia;
    }

    public ArrayList<AgendaHoraTurno> getListAgendaHoraTurno() {
        return listAgendaHoraTurno;
    }

    public void setListAgendaHoraTurno(ArrayList<AgendaHoraTurno> listAgendaHoraTurno) {
        this.listAgendaHoraTurno = listAgendaHoraTurno;
    }

    public AgendaHoraTurno getAgendaHoraTurno() {
        return agendaHoraTurno;
    }

    public void setAgendaHoraTurno(AgendaHoraTurno agendaHoraTurno) {
        this.agendaHoraTurno = agendaHoraTurno;
    }

    public ArrayList<SelectStringValue> getListIntervalos() {
        return listIntervalos;
    }

    public void setListIntervalos(ArrayList<SelectStringValue> listIntervalos) {
        this.listIntervalos = listIntervalos;
    }

    public SelectStringValue getSelectedIntervalo() {
        return selectedIntervalo;
    }

    public void setSelectedIntervalo(SelectStringValue selectedIntervalo) {
        this.selectedIntervalo = selectedIntervalo;
    }

    public Date getDesdeHoraSel() {
        return desdeHoraSel;
    }

    public void setDesdeHoraSel(Date desdeHoraSel) {
        this.desdeHoraSel = desdeHoraSel;
    }

    public Date getHastaHoraSel() {
        return hastaHoraSel;
    }

    public void setHastaHoraSel(Date hastaHoraSel) {
        this.hastaHoraSel = hastaHoraSel;
    }

    private Date obtenerDiaHoyHorayMinutoCero() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }

    public ArrayList<ConsultaDoctor> getListConsultas() {
        return listConsultas;
    }

    public void setListConsultas(ArrayList<ConsultaDoctor> listConsultas) {
        this.listConsultas = listConsultas;
    }

    public SelectStringValue getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(SelectStringValue selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public ArrayList<SelectStringValue> getListConsultorios() {
        return listConsultorios;
    }

    public void setListConsultorios(ArrayList<SelectStringValue> listConsultorios) {
        this.listConsultorios = listConsultorios;
    }

    public SelectStringValue getSelectedConsultorio() {
        return selectedConsultorio;
    }

    public void setSelectedConsultorio(SelectStringValue selectedConsultorio) {
        this.selectedConsultorio = selectedConsultorio;
    }

    public Date getToday() {
        return today;
    }

    public ArrayList<Agenda> getListaAgendaGenerada() {
        return listaAgendaGenerada;
    }

    public void setListaAgendaGenerada(ArrayList<Agenda> listaAgendaGenerada) {
        this.listaAgendaGenerada = listaAgendaGenerada;
    }

    public ArrayList<SelectStringValue> getListDoctores() {
        return listDoctores;
    }

    public void setListDoctores(ArrayList<SelectStringValue> listDoctores) {
        this.listDoctores = listDoctores;
    }
    
    public void reset(){
        this.listDiasParaTurnos = null;
        this.diaSelected = null;
        this.today = new Date();
        this.agendaHoraTurno = null;
        this.selectedIntervalo = null;
        this.selectedDoctor = null;
        this.selectedConsultorio= null;
        this.desdeHoraSel = obtenerDiaHoyHorayMinutoCero();
        this.hastaHoraSel = obtenerDiaHoyHorayMinutoCero();
        this.listaAgendaGenerada = new ArrayList<Agenda>();
        this.listDoctores= new ArrayList<SelectStringValue>();
        this.listConsultas  = new ArrayList<ConsultaDoctor>();
        this.listConsultorios = new ArrayList<SelectStringValue>();
        this.listAgendaDia = new ArrayList<AgendaDia>();
        this.listAgendaHoraTurno = new ArrayList<AgendaHoraTurno>();
    }
}
