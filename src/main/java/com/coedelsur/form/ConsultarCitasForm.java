package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.Consulta;
import com.coedelsur.model.Paciente;

public class ConsultarCitasForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Consulta> listMisConsultas;
    private Consulta selectedConsulta;
    private Paciente paciente;

    public ConsultarCitasForm() {
    }

    public ArrayList<Consulta> getListMisConsultas() {
        return listMisConsultas;
    }

    public void setListMisConsultas(ArrayList<Consulta> listMisConsultas) {
        this.listMisConsultas = listMisConsultas;
    }

    public Consulta getSelectedConsulta() {
        return selectedConsulta;
    }

    public void setSelectedConsulta(Consulta selectedConsulta) {
        this.selectedConsulta = selectedConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
