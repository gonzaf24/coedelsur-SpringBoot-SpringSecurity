package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.ConsultaActual;

public class ConsultaActualForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<ConsultaActual> listConsultaActual = new ArrayList<ConsultaActual>();
    private ConsultaActual consultaActual = new ConsultaActual();
    private ConsultaActual consultaActualEdit = new ConsultaActual();
    private ConsultaActual consultaActualSelected = new ConsultaActual();

    public ConsultaActualForm() {
    }

    public ArrayList<ConsultaActual> getListConsultaActual() {
        return listConsultaActual;
    }

    public void setListConsultaActual(ArrayList<ConsultaActual> listConsultaActual) {
        this.listConsultaActual = listConsultaActual;
    }

    public ConsultaActual getConsultaActual() {
        return consultaActual;
    }

    public void setConsultaActual(ConsultaActual consultaActual) {
        this.consultaActual = consultaActual;
    }

    public ConsultaActual getConsultaActualEdit() {
        return consultaActualEdit;
    }

    public void setConsultaActualEdit(ConsultaActual consultaActualEdit) {
        this.consultaActualEdit = consultaActualEdit;
    }

    public ConsultaActual getConsultaActualSelected() {
        return consultaActualSelected;
    }

    public void setConsultaActualSelected(ConsultaActual consultaActualSelected) {
        this.consultaActualSelected = consultaActualSelected;
    }

    public void reset() {
        this.consultaActual= new ConsultaActual();
        this.consultaActualEdit = new ConsultaActual();
        this.consultaActualSelected = new ConsultaActual();
        this.listConsultaActual = new ArrayList<ConsultaActual>();
    }
}
