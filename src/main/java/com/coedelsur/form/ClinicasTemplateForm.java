package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.Doctor;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;

public class ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private Paciente paciente;
    private Doctor doctor;
    private ArrayList<SelectStringValue> departamentoList;
    private ArrayList<SelectStringValue> tipoConsultaList;
    private ArrayList<SelectStringValue> especialidadesList;
    private ArrayList<Doctor> doctoresList;
    private ArrayList<String> logueosList;

    public ClinicasTemplateForm() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ArrayList<SelectStringValue> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(ArrayList<SelectStringValue> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public ArrayList<SelectStringValue> getTipoConsultaList() {
        return tipoConsultaList;
    }

    public void setTipoConsultaList(ArrayList<SelectStringValue> tipoConsultaList) {
        this.tipoConsultaList = tipoConsultaList;
    }

    public ArrayList<SelectStringValue> getEspecialidadesList() {
        return especialidadesList;
    }

    public void setEspecialidadesList(ArrayList<SelectStringValue> especialidadesList) {
        this.especialidadesList = especialidadesList;
    }

    public ArrayList<Doctor> getDoctoresList() {
        return doctoresList;
    }

    public void setDoctoresList(ArrayList<Doctor> doctoresList) {
        this.doctoresList = doctoresList;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public ArrayList<String> getLogueosList() {
        return logueosList;
    }

    public void setLogueosList(ArrayList<String> logueosList) {
        this.logueosList = logueosList;
    }
}
