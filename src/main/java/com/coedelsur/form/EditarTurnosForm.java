package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.Agenda;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;

public class EditarTurnosForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Agenda> listaAgendaEditarTurno = new ArrayList<Agenda>();
    private Paciente selectedPaciente;
    private Agenda selectedTurnoEliminar;
    private SelectStringValue selectedDoctor;
    private ArrayList<SelectStringValue> listDoctores = new ArrayList<SelectStringValue>();

    public EditarTurnosForm() {
    }

    public ArrayList<Agenda> getListaAgendaEditarTurno() {
        return listaAgendaEditarTurno;
    }

    public void setListaAgendaEditarTurno(ArrayList<Agenda> listaAgendaEditarTurno) {
        this.listaAgendaEditarTurno = listaAgendaEditarTurno;
    }

    public Paciente getSelectedPaciente() {
        return selectedPaciente;
    }

    public void setSelectedPaciente(Paciente selectedPaciente) {
        this.selectedPaciente = selectedPaciente;
    }

    public Agenda getSelectedTurnoEliminar() {
        return selectedTurnoEliminar;
    }

    public void setSelectedTurnoEliminar(Agenda selectedTurnoEliminar) {
        this.selectedTurnoEliminar = selectedTurnoEliminar;
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
    
    public void reset(){
        this.listaAgendaEditarTurno = new ArrayList<Agenda>();
        this.listDoctores = new ArrayList<SelectStringValue>();
        this.selectedDoctor = null;
        this.selectedPaciente = null;
        this.selectedTurnoEliminar = null;
    }
}
