package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.Paciente;

public class BusquedaPacienteForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellidos;
    private Integer ci;
    private ArrayList<Paciente> pacientesList = new ArrayList<Paciente>();

    public BusquedaPacienteForm() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public ArrayList<Paciente> getPacientesList() {
        return pacientesList;
    }

    public void setPacientesList(ArrayList<Paciente> pacientesList) {
        this.pacientesList = pacientesList;
    }
}
